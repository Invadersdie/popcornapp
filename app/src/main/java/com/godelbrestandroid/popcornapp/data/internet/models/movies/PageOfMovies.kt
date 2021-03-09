package com.godelbrestandroid.popcornapp.data.internet.models.movies

import com.google.gson.annotations.SerializedName

data class PageOfMovies(
    val page: Int,
    @SerializedName("results")
    val moviesList: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)

