package com.dxn.github.repos.ui.screens.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxn.github.repos.common.models.Readme
import com.dxn.github.repos.common.models.Repo
import com.dxn.github.repos.common.util.Resource
import com.dxn.github.repos.domain.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
@Inject
constructor(private val repository: GithubRepository) : ViewModel() {

    private val _readme: MutableState<Readme?> = mutableStateOf(null)
    val readme: State<Readme?> get() = _readme

    private val _repo: MutableState<Repo?> = mutableStateOf(null)
    val repo: State<Repo?> get() = _repo

    fun setRepo(repo: Repo) {
        if (repo != _repo.value) {
            _repo.value = repo
            loadReadme(repo)
        }
    }

    private fun loadReadme(repo: Repo) {
        viewModelScope.launch {
            repository.getReadme(repo).collect { res ->
                when (res) {
                    is Resource.Success -> {
                        _readme.value = res.data!!
                    }
                }
            }
        }
    }
}