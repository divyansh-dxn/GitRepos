package com.dxn.github.repos.ui.screens.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dxn.github.repos.common.Resource
import com.dxn.github.repos.data.models.repo.Repo
import com.dxn.github.repos.domain.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel
@Inject
constructor(private val repository: GithubRepository) : ViewModel() {

    var job: Job? = null

    private val _repo: MutableLiveData<Repo> = MutableLiveData()
    val repo get() = _repo

    private val _error: MutableLiveData<String?> = MutableLiveData(null)
    val error get() = _error

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoading get() = _isLoading

    fun loadRepo(userName: String, repoName: String) {
        job?.cancel()
        job = viewModelScope.launch {
            repository.getRepoDetails(userName, repoName).collect { res ->
                Log.d("TAG", "loadRepo: ${res.data} ${res.message}")
                when (res) {
                    is Resource.Success -> {
                        _repo.value = res.data!!
                        _error.value = null
                        _isLoading.value = false
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                        _error.value = null
                    }
                    is Resource.Failure -> {
                        _error.value = res.message
                        _isLoading.value = false
                    }
                }
            }
        }
    }

}