package com.dxn.github.repos.data.api

import retrofit2.http.GET
import retrofit2.http.Path

sealed interface GithubUserContentApi {
    @GET("/{ownerName}/{repoName}/{defaultBranch}/README.md")
    suspend fun getReadme(
        @Path("ownerName") ownerName: String,
        @Path("repoName") repoName: String,
        @Path("defaultBranch") defaultBranch: String,
    ): String
}