package com.godelbrestandroid.popcornapp.data.internet.models.people

import com.godelbrestandroid.popcornapp.data.internet.models.KnownFor

data class Person(
    val adult: Boolean,
    val also_known_as: List<String>,
    val known_for: List<KnownFor>,
    val biography: String,
    val birthday: String?,
    val deathday: String?,
    val gender: Int,
    val homepage: String?,
    val id: Int,
    val imdb_id: String,
    val known_for_department: String,
    val name: String,
    val place_of_birth: String?,
    val popularity: Double,
    val profile_path: String?
)