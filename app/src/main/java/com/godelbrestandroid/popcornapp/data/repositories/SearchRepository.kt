package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.internet.models.movies.PageOfMovies
import com.godelbrestandroid.popcornapp.data.internet.models.people.PageOfPeople
import com.godelbrestandroid.popcornapp.data.internet.models.search.MultiSearchResult
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.PageOfTvShows
import io.reactivex.Single

interface SearchRepository {
    fun downloadMultiSearchResult(text: String): Single<MultiSearchResult>
    fun downloadMoviesSearchResult(text: String): Single<PageOfMovies>
    fun downloadTvShowsSearchResult(text: String): Single<PageOfTvShows>
    fun downloadPeopleSearchResult(text: String): Single<PageOfPeople>
}