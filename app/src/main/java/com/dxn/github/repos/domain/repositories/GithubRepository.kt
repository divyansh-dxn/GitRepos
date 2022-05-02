package com.dxn.github.repos.domain.repositories

import androidx.paging.PagingData
import com.dxn.github.repos.common.models.Organization
import com.dxn.github.repos.common.models.Repo
import com.dxn.github.repos.common.models.RepoSort
import com.dxn.github.repos.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
//    suspend fun getRepoDetails(userName: String, repoName: String): Flow<Resource<Repo>>
    fun getAllRepos(orgName: Organization, sort: RepoSort): Flow<PagingData<Repo>>
    suspend fun getReadme(repo: Repo): Flow<Resource<String>>
}