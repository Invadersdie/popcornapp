package com.godelbrestandroid.popcornapp.data.models

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Movie

data class MoviesCombined(
    val popularMovies: List<Movie>,
    val nowPlayingMovies: List<Movie>,
    val topRatedMovies: List<Movie>,
    val upcomingMovies: List<Movie>
)
