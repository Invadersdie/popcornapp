package com.godelbrestandroid.popcornapp.data.models

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Movie
import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow

data class HomeCombined(
    val movies: List<Movie>,
    val tvShows: List<TvShow>,
    val people: List<Person>
)