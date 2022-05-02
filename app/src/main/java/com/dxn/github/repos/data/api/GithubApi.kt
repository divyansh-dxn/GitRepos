package com.dxn.github.repos.data.api

import com.dxn.github.repos.common.models.Readme
import com.dxn.github.repos.common.models.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

sealed interface GithubApi {

    @GET("/orgs/{orgName}/repos")
    suspend fun getRepos(
        @Path("orgName") orgName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 10,
        @Query("sort") sort: String = "updated"
    ): List<Repo>

    @GET("/repos/{userName}/{repoName}")
    suspend fun getRepo(
        @Path("userName") userName: String,
        @Path("repoName") repoName: String
    ): Repo

    @GET("/repos/{orgName}/{repoName}/readme")
    suspend fun getReadme(
        @Path("orgName") userName: String,
        @Path("repoName") repoName: String
    ): Readme
}