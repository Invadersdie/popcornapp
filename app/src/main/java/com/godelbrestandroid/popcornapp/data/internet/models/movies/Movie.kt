package com.godelbrestandroid.popcornapp.data.internet.models.movies

import com.godelbrestandroid.popcornapp.data.internet.models.Genre
import com.godelbrestandroid.popcornapp.data.internet.models.ProductionCompany

data class Movie(
    val adult: Boolean,
    val backdrop_path: String?,
    val belongs_to_collection: Any?,
    val budget: Int = 0,
    val genres: List<Genre> = emptyList(),
    val genre_ids: List<Int> = emptyList(),
    val homepage: String?,
    val id: Int,
    val imdb_id: String?,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String?,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Int,
    val runtime: Int?,
    val spoken_languages: List<SpokenLanguage>,
    val status: Status?,
    val tagline: String?,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)

