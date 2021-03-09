package com.godelbrestandroid.popcornapp.data.mappers

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Movie
import com.godelbrestandroid.popcornapp.data.internet.models.movies.MovieCredits
import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import com.godelbrestandroid.popcornapp.data.internet.models.search.MultiSearchResult
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow
import com.godelbrestandroid.popcornapp.data.models.ShortInfo


interface ShortInfoMapper {
    fun movieCreditsToShortList(movieCredits: MovieCredits): List<ShortInfo>
    fun pageOfPeopleToShortInfoList(listPeople: List<Person>): List<ShortInfo>
    fun pageOfMoviesToShortInfoList(listMovie: List<Movie>): List<ShortInfo>
    fun pageOfMoviesWithRateToShortInfoList(listMovie: List<Movie>): List<ShortInfo>
    fun pageOfTvShowsToShortInfoList(listTvShows: List<TvShow>): List<ShortInfo>
    fun multiSearchResultToShortInfoList(multiSearchResult: MultiSearchResult): List<ShortInfo>
}