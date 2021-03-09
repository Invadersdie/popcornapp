package com.godelbrestandroid.popcornapp.data.internet.models.tvshows

import com.google.gson.annotations.SerializedName

data class PageOfTvShows(
    val page: Int,
    @SerializedName("results")
    val tvShowsList: List<TvShow>,
    val total_pages: Int,
    val total_results: Int
)

