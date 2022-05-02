package com.dxn.github.repos.common.models


data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<Repo>,
    val total_count: Int
)