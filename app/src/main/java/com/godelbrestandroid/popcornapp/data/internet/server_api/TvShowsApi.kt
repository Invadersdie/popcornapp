package com.godelbrestandroid.popcornapp.data.internet.server_api

import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.PageOfTvShows
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowsApi {

    @GET("tv/{tv_id}")
    fun getTvShowsDetails(@Path("tv_id") tvId: Int): Single<TvShow>


    @GET("tv/popular")
    fun getPopularTvShows(@Query("page") pageToQuery: Int = 1): Single<PageOfTvShows>

    @GET("tv/airing_today")
    fun getAiringTvShows(@Query("page") pageToQuery: Int = 1): Single<PageOfTvShows>

    @GET("tv/top_rated")
    fun getTopRatedTvShows(@Query("page") pageToQuery: Int = 1): Single<PageOfTvShows>

    @GET("tv/on_the_air")
    fun getOnTheAirTvShows(@Query("page") pageToQuery: Int = 1): Single<PageOfTvShows>

}