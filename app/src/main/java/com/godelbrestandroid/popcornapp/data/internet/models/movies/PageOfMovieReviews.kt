package com.godelbrestandroid.popcornapp.data.internet.models.movies

import com.google.gson.annotations.SerializedName

data class PageOfMovieReviews(
    val id: Int,
    val page: Int,
    @SerializedName("results")
    val reviews: List<Review>,
    val total_pages: Int,
    val total_results: Int
)