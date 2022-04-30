package com.dxn.github.repos.common.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Repo(
    val owner: Owner,
    val name: String,
    val default_branch: String,
    val id: Int,
    val full_name: String,
    val stargazers_count: Int
) : Parcelable