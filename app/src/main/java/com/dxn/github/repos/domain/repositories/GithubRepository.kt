package com.dxn.github.repos.domain.repositories

import androidx.paging.PagingData
import com.dxn.github.repos.common.models.Readme
import com.dxn.github.repos.common.models.Repo
import com.dxn.github.repos.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    //suspend fun getRepoDetails(userName: String, repoName: String): Flow<Resource<Repo>>
    //fun getAllRepos(orgName: String, sortBy: String): Flow<PagingData<Repo>>
    fun getReadme(repo: Repo): Flow<Resource<Readme>>
    fun searchRepos(query: String, sort: String): Flow<PagingData<Repo>>
}