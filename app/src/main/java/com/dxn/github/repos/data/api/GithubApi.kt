package com.dxn.github.repos.data.api

import com.dxn.github.repos.data.models.repo.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {

    @GET("/")
    suspend fun getRepos()

    @GET("/repos/{userName}/{repoName}")
    suspend fun getRepo(
        @Path("userName") userName: String,
        @Path("repoName") repoName: String
    ): Repo
}
