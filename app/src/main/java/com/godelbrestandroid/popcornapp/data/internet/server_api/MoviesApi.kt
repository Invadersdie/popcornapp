package com.godelbrestandroid.popcornapp.data.internet.server_api


import com.godelbrestandroid.popcornapp.data.internet.models.movies.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApi {

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: Int): Single<Movie>

    @GET("movie/{movie_id}/images")
    fun getMovieImages(@Path("movie_id") movieId: Int): Single<MovieImages>

    @GET("movie/{movie_id}/credits")
    fun getMovieCredits(@Path("movie_id") movieId: Int): Single<MovieCredits>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReviews(@Path("movie_id") movieId: Int): Single<PageOfMovieReviews>

    @GET("movie/{movie_id}/recommendations")
    fun getMovieRecommendations(@Path("movie_id") movieId: Int): Single<PageOfMovies>

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") pageToQuery: Int = 1): Single<PageOfMovies>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") pageToQuery: Int = 1): Single<PageOfMovies>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") pageToQuery: Int = 1): Single<PageOfMovies>

    @GET("movie/upcoming")
    fun getUpcomingMovies(@Query("page") pageToQuery: Int = 1): Single<PageOfMovies>


}