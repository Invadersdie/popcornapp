package com.godelbrestandroid.popcornapp.data.internet.models.tvshows

import com.godelbrestandroid.popcornapp.data.internet.models.Genre
import com.godelbrestandroid.popcornapp.data.internet.models.ProductionCompany

data class TvShow(
    val backdrop_path: String? = "",
    val created_by: List<CreatedBy> = emptyList(),
    val episode_run_time: List<Int> = emptyList(),
    val first_air_date: String = "",
    val genres: List<Genre> = emptyList(),
    val genre_ids: List<Int> = emptyList(),
    val homepage: String = "",
    val id: Int = 0,
    val in_production: Boolean = true,
    val languages: List<String> = emptyList(),
    val last_air_date: String = "",
    val last_episode_to_air: LastEpisodeToAir,
    val name: String = "",
    val networks: List<Network> = emptyList(),
    val next_episode_to_air: Any? = null,
    val number_of_episodes: Int = 0,
    val number_of_seasons: Int = 0,
    val origin_country: List<String> = emptyList(),
    val original_language: String = "",
    val original_name: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String? = "",
    val production_companies: List<ProductionCompany> = emptyList(),
    val seasons: List<Season> = emptyList(),
    val status: String = "",
    val type: String = "",
    val vote_average: Double = 0.0,
    val vote_count: Int = 0
)