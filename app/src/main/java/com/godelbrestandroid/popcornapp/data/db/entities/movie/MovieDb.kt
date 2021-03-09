package com.godelbrestandroid.popcornapp.data.db.entities.movie

import com.godelbrestandroid.popcornapp.data.internet.models.Genre
import com.godelbrestandroid.popcornapp.data.internet.models.ProductionCompany
import com.godelbrestandroid.popcornapp.data.internet.models.movies.*
import io.objectbox.Box
import io.objectbox.annotation.Backlink
import io.objectbox.annotation.Convert
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.relation.ToMany

@Entity
data class MovieDb(
    @Id(assignable = true) var id: Long = 0,
    var adult: Boolean = true,
    var backdrop_path: String? = null,
    var budget: Int = 0,
    var homepage: String? = null,
    var imdb_id: String? = null,
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: Double = 0.0,
    var poster_path: String? = "",
    var release_date: String = "",
    var revenue: Int = 0,
    var runtime: Int? = null,
    @Convert(dbType = String::class, converter = StatusConverter::class)
    var status: Status? = null,
    var tagline: String? = null,
    var title: String = "",
    var video: Boolean = true,
    var vote_average: Double = 0.0,
    var vote_count: Int = 0,
    var upcomingPage: Int = 0,
    var topRatedPage: Int = 0,
    var popularPage: Int = 0,
    var nowPlayingPage: Int = 0
) {
    lateinit var genres: ToMany<Genre>
    lateinit var production_companies: ToMany<ProductionCompany>
    lateinit var production_countries: ToMany<ProductionCountry>
    lateinit var spoken_languages: ToMany<SpokenLanguage>
    lateinit var recommendations: ToMany<MovieDb>
    @Backlink
    lateinit var reviews: ToMany<Review>
}

fun MovieDb.toMovie(): Movie {
    return Movie(
        this.adult,
        this.backdrop_path,
        this.backdrop_path,
        this.budget,
        this.genres,
        emptyList(),
        this.homepage,
        this.id.toInt(),
        this.imdb_id,
        this.original_language,
        this.original_title,
        this.overview,
        this.popularity,
        this.poster_path,
        this.production_companies,
        this.production_countries,
        this.release_date,
        this.revenue,
        this.runtime,
        this.spoken_languages,
        this.status,
        this.tagline,
        this.original_title,
        this.video,
        this.vote_average,
        this.vote_count
    )
}

fun Movie.toMovieDb(box: Box<MovieDb>): MovieDb {
    var nowPlayingPage = 0
    var popularPage = 0
    var topRatedPage = 0
    var upcomingPage = 0
    val movieDbPages: MovieDb? = box.get(id.toLong())
    if (movieDbPages != null) {
        nowPlayingPage = movieDbPages.nowPlayingPage
        popularPage = movieDbPages.popularPage
        topRatedPage = movieDbPages.topRatedPage
        upcomingPage = movieDbPages.upcomingPage
    }
    val movieDb = MovieDb(
        id = this.id.toLong(),
        adult = this.adult,
        backdrop_path = this.backdrop_path,
        budget = this.budget,
        homepage = this.homepage,
        imdb_id = this.imdb_id,
        original_language = this.original_language,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        revenue = this.revenue,
        runtime = this.runtime,
        status = this.status,
        tagline = this.tagline,
        title = this.title,
        video = this.video,
        vote_average = this.vote_average,
        vote_count = this.vote_count,
        nowPlayingPage = nowPlayingPage,
        popularPage = popularPage,
        topRatedPage = topRatedPage,
        upcomingPage = upcomingPage
    )
    box.attach(movieDb)
    return movieDb
}