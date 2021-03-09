package com.godelbrestandroid.popcornapp.data.internet.models.movies

import com.google.gson.annotations.SerializedName

data class MovieCredits(
    @SerializedName("cast")
    val actorsList: List<Actor>,
    val crew: List<Crew>,
    val id: Int
)