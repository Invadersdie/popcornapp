package com.godelbrestandroid.popcornapp.data.db.entities.movie

import com.godelbrestandroid.popcornapp.data.db.entities.ListStringConverter
import com.godelbrestandroid.popcornapp.data.internet.models.Genre
import com.godelbrestandroid.popcornapp.data.internet.models.ListIntConverter
import com.godelbrestandroid.popcornapp.data.internet.models.ProductionCompany
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.*
import io.objectbox.Box
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Transient
import io.objectbox.relation.ToMany

@Entity
data class TvShowDb(
    var backdrop_path: String? = "",
    @Convert(dbType = String::class, converter = ListIntConverter::class)
    var episode_run_time: List<Int> = emptyList(),
    var first_air_date: String = "",
    @Transient
    var genre_ids: List<Int> = emptyList(),
    var homepage: String = "",
    @Id(assignable = true) var id: Long = 0,
    var in_production: Boolean = true,
    @Convert(dbType = String::class, converter = ListStringConverter::class)
    var languages: List<String> = emptyList(),
    var last_air_date: String = "",
    @Transient
    var last_episode_to_air: LastEpisodeToAir = LastEpisodeToAir(),
    var name: String = "",
    @Transient
    var next_episode_to_air: Any? = null,
    var number_of_episodes: Int = 0,
    var number_of_seasons: Int = 0,
    @Convert(dbType = String::class, converter = ListStringConverter::class)
    var origin_country: List<String> = emptyList(),
    var original_language: String = "",
    var original_name: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var poster_path: String? = "",
    var status: String = "",
    var type: String = "",
    var vote_average: Double = 0.0,
    var vote_count: Int = 0,
    var popularPage: Int = 0,
    var airingPage: Int = 0,
    var onTheAirPage: Int = 0,
    var topRatedPage: Int = 0
) {
    lateinit var created_by: ToMany<CreatedBy>
    lateinit var genres: ToMany<Genre>
    lateinit var networks: ToMany<Network>
    lateinit var production_companies: ToMany<ProductionCompany>
    lateinit var seasons: ToMany<Season>
    lateinit var recommendations: ToMany<TvShowDb>
}

fun TvShow.toTvShowDb(box: Box<TvShowDb>): TvShowDb {
    val tvShowDb = TvShowDb(
        backdrop_path = this.backdrop_path,
        first_air_date = this.first_air_date,
        genre_ids = emptyList(),
        id = this.id.toLong(),
        name = this.name,
        origin_country = this.origin_country,
        original_language = this.original_language,
        original_name = this.original_name,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        vote_average = this.vote_average,
        vote_count = this.vote_count
    )
    box.attach(tvShowDb)
    return tvShowDb
}

fun TvShowDb.toTvShow(): TvShow {
    return TvShow(
        backdrop_path = backdrop_path,
        episode_run_time = episode_run_time,
        first_air_date = first_air_date,
        genre_ids = genre_ids,
        homepage = homepage,
        id = id.toInt(),
        in_production = in_production,
        languages = languages,
        last_air_date = last_air_date,
        last_episode_to_air = last_episode_to_air,
        name = name,
        next_episode_to_air = next_episode_to_air,
        number_of_episodes = number_of_episodes,
        number_of_seasons = number_of_seasons,
        origin_country = origin_country,
        original_language = original_language,
        original_name = original_name,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        status = status,
        type = type,
        vote_count = vote_count,
        vote_average = vote_average,
        created_by = created_by,
        genres = genres,
        networks = networks,
        production_companies = production_companies,
        seasons = seasons
    )
}