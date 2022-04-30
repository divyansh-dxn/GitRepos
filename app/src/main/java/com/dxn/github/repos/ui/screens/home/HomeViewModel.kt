package com.dxn.github.repos.ui.screens.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.dxn.github.repos.common.util.Organization
import com.dxn.github.repos.common.util.RepoSort
import com.dxn.github.repos.domain.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.paging.cachedIn
import com.dxn.github.repos.common.models.Repo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val repository: GithubRepository) : ViewModel() {

    private val _repoSort: MutableLiveData<RepoSort> = MutableLiveData(RepoSort.CREATED)
    val repoSort: LiveData<RepoSort> get() = _repoSort

    private val _org: MutableLiveData<Organization> = MutableLiveData(Organization.JETBRAINS)
    val org: LiveData<Organization> get() = _org

    fun sortBy(repoSort: RepoSort) {
        _repoSort.value = repoSort
    }

    fun setOrganization(organization: Organization) {
        _org.value = organization
    }

    fun getData() : Flow<PagingData<Repo>> =
        repository.getAllRepos(_org.value!!,_repoSort.value!!).cachedIn(viewModelScope)


}