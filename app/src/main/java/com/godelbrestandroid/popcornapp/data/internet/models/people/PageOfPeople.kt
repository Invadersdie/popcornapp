package com.godelbrestandroid.popcornapp.data.internet.models.people

import com.google.gson.annotations.SerializedName

data class PageOfPeople(
    val page: Int,
    @SerializedName("results")
    val popularPeople: List<Person>,
    val total_pages: Int,
    val total_results: Int
)

