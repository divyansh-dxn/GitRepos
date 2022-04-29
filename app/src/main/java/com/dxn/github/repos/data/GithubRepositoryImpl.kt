package com.dxn.github.repos.data

import android.util.Log
import com.dxn.github.repos.common.Resource
import com.dxn.github.repos.data.api.GithubApi
import com.dxn.github.repos.data.models.repo.Repo
import com.dxn.github.repos.domain.repositories.GithubRepository
import kotlinx.coroutines.flow.flow

class GithubRepositoryImpl(
    private val api: GithubApi
) : GithubRepository {
    override suspend fun getAllRepos() {

    }

    override suspend fun getRepoDetails(userName: String, repoName: String) = flow<Resource<Repo>> {
        emit(Resource.Loading())
        runCatching {
            val repo = api.getRepo(userName, repoName)
            emit(Resource.Success(repo))
        }.getOrElse {
            Log.e(TAG, "getRepoDetails: ", it)
            emit(Resource.Failure(it.message.toString()))
        }
    }

    companion object {
        private const val TAG = "GITHUB_REPOSITORY"
    }
}