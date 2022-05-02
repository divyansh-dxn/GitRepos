package com.dxn.github.repos.common.util

sealed class RepoSort {
    object UPDATED : RepoSort() {
        override fun invoke() = "updated"
    }

    object CREATED : RepoSort() {
        override fun invoke() = "created"
    }

    object PUSHED : RepoSort() {
        override fun invoke() = "pushed"
    }

    object NAME : RepoSort() {
        override fun invoke() = "full_name"
    }

    abstract operator fun invoke(): String
}

val sortList = listOf(
    RepoSort.UPDATED, RepoSort.CREATED, RepoSort.PUSHED, RepoSort.NAME
)

