package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Movie
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieCredits
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieImages
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Review
import io.reactivex.Observable

interface MoviesRepository {
    fun getPopularMovies(page: Int = 1): Observable<List<Movie>>
    fun getNowPlayingMovies(page: Int = 1): Observable<List<Movie>>
    fun getTopRatedMovies(page: Int = 1): Observable<List<Movie>>
    fun getUpcomingMovies(page: Int = 1): Observable<List<Movie>>
    fun getMovieDetails(id: Int): Observable<Movie>
    fun getMovieImages(id: Int): Observable<MovieImages>
    fun getMovieCredits(id: Int): Observable<MovieCredits>
    fun getMovieReviews(id: Int): Observable<List<Review>>
    fun getMovieRecommendations(id: Int): Observable<List<Movie>>
}