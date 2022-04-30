package com.dxn.github.repos.common.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Owner(
    val avatar_url: String,
    val id: Int,
    val login: String,
) : Parcelable