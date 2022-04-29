package com.dxn.github.repos.ui.screens.home

import androidx.lifecycle.ViewModel
import com.dxn.github.repos.domain.repositories.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(private val repository: GithubRepository) : ViewModel() {

}