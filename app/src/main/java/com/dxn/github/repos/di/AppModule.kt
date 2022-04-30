package com.dxn.github.repos.di

import com.dxn.github.repos.data.GithubRepositoryImpl
import com.dxn.github.repos.data.api.GithubApi
import com.dxn.github.repos.data.api.GithubUserContentApi
import com.dxn.github.repos.domain.repositories.GithubRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideGithubApi(): GithubApi = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubApi::class.java)

    @Provides
    @Singleton
    fun provideReadmeApi(): GithubUserContentApi = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GithubUserContentApi::class.java)

    @Provides
    @Singleton
    fun provideGithubRepository(
        api: GithubApi,
        contentApi: GithubUserContentApi
    ): GithubRepository = GithubRepositoryImpl(api, contentApi)
}