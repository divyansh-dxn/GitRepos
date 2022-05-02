package com.dxn.github.repos.data

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.dxn.github.repos.common.models.Organization
import com.dxn.github.repos.common.util.Resource
import com.dxn.github.repos.common.models.Repo
import com.dxn.github.repos.common.models.RepoSort
import com.dxn.github.repos.data.api.GithubApi
import com.dxn.github.repos.data.api.GithubUserContentApi
import com.dxn.github.repos.domain.repositories.GithubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GithubRepositoryImpl(
    private val api: GithubApi,
    private val contentApi: GithubUserContentApi
) : GithubRepository {
    override fun getAllRepos(orgName: Organization, sort: RepoSort): Flow<PagingData<Repo>> = Pager(
        config = PagingConfig(
            pageSize = NETWORK_PAGE_SIZE,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ReposPagingSource(api, orgName) }
    ).flow

    override suspend fun getReadme(repo: Repo) = flow<Resource<String>> {
        emit(Resource.Loading())
        runCatching {
            val readme = contentApi.getReadme(repo.owner.login, repo.name, repo.default_branch)
            emit(Resource.Success(readme))
        }.getOrElse {
            Log.e(TAG, "getRepoDetails: ", it)
            emit(Resource.Failure(it.message.toString()))
        }
    }

//    override suspend fun getRepoDetails(userName: String, repoName: String) = flow<Resource<Repo>> {
//        emit(Resource.Loading())
//        runCatching {
//            val repo = api.getRepo(userName, repoName)
//            emit(Resource.Success(repo))
//        }.getOrElse {
//            Log.e(TAG, "getRepoDetails: ", it)
//            emit(Resource.Failure(it.message.toString()))
//        }
//    }

    companion object {
        private const val TAG = "GITHUB_REPOSITORY"
        const val NETWORK_PAGE_SIZE = 50
    }
}