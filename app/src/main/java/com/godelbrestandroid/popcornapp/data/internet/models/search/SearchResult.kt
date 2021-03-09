package com.godelbrestandroid.popcornapp.data.internet.models.search

import com.godelbrestandroid.popcornapp.data.internet.models.KnownFor
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType

data class SearchResult(
    val adult: Boolean,
    val backdrop_path: String?,
    val first_air_date: String,
    val genre_ids: List<Any>,
    val id: Int,
    val known_for: List<KnownFor>,
    val media_type: MediaType,
    val name: String,
    val origin_country: List<Any>,
    val original_language: String,
    val original_name: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val profile_path: String?,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)