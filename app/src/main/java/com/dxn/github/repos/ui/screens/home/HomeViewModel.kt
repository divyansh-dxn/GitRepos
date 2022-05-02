package com.dxn.github.repos.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dxn.github.repos.common.models.Repo
import com.dxn.github.repos.domain.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val repository: GithubRepository) : ViewModel() {

    fun searchRepos(query: String, sort: String): Flow<PagingData<Repo>> =
        repository.searchRepos(query, sort).cachedIn(viewModelScope)

}