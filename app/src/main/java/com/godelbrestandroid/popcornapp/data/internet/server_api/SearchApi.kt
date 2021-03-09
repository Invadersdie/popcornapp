package com.godelbrestandroid.popcornapp.data.internet.server_api

import com.godelbrestandroid.popcornapp.data.internet.models.movies.PageOfMovies
import com.godelbrestandroid.popcornapp.data.internet.models.people.PageOfPeople
import com.godelbrestandroid.popcornapp.data.internet.models.search.MultiSearchResult
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.PageOfTvShows
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/multi")
    fun getMultiSearchResult(@Query("query") text: String): Single<MultiSearchResult>

    @GET("search/movie")
    fun getMoviesSearchResult(@Query("query") text: String): Single<PageOfMovies>

    @GET("search/tv")
    fun getTvShowsSearchResult(@Query("query") text: String): Single<PageOfTvShows>

    @GET("search/person")
    fun getPeopleSearchResult(@Query("query") text: String): Single<PageOfPeople>
}