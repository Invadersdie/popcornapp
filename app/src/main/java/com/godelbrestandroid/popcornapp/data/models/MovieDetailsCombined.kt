package com.godelbrestandroid.popcornapp.data.models

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Movie
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieCredits
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieImages
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Review

data class MovieDetailsCombined(
    val movie: Movie,
    val movieImages: MovieImages,
    val movieCredits: MovieCredits,
    val movieReviews: List<Review>,
    val movieRecommendations: List<Movie>
)