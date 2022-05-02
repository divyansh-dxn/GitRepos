package com.dxn.github.repos.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dxn.github.repos.common.models.Repo
import com.dxn.github.repos.data.GithubRepositoryImpl.Companion.NETWORK_PAGE_SIZE
import com.dxn.github.repos.data.api.GithubApi
import retrofit2.HttpException
import java.io.IOException

class ReposPagingSource(
    private val api: GithubApi,
    private val orgName: String,
    private val repoSort: String
) : PagingSource<Int, Repo>() {
    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        val page = params.key ?: GITHUB_STARTING_PAGE_INDEX
        return try {
            val repos = api.getRepos(orgName,page,params.loadSize,repoSort)
            val nextKey = if (repos.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                page + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = repos,
                prevKey = if (page == GITHUB_STARTING_PAGE_INDEX) null else page - 1,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val GITHUB_STARTING_PAGE_INDEX = 1
    }
}


