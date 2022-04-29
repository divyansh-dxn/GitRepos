package com.dxn.github.repos.domain.repositories

import com.dxn.github.repos.common.Resource
import com.dxn.github.repos.data.models.repo.Repo
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    suspend fun getAllRepos()
    suspend fun getRepoDetails(userName: String, repoName: String): Flow<Resource<Repo>>
}