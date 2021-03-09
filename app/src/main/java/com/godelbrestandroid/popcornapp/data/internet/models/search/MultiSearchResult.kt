package com.godelbrestandroid.popcornapp.data.internet.models.search

import com.google.gson.annotations.SerializedName

data class MultiSearchResult(
    val page: Int,
    @SerializedName("results")
    val searchResults: List<SearchResult>,
    val total_pages: Int,
    val total_results: Int
)