package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.internet.models.movies.PageOfMovies
import com.godelbrestandroid.popcornapp.data.internet.models.people.PageOfPeople
import com.godelbrestandroid.popcornapp.data.internet.models.search.MultiSearchResult
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.PageOfTvShows
import com.godelbrestandroid.popcornapp.data.internet.server_api.SearchApi
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor() : SearchRepository {


    @Inject
    lateinit var searchApi: SearchApi

    override fun downloadMultiSearchResult(text: String): Single<MultiSearchResult> {
        return searchApi.getMultiSearchResult(text)
    }

    override fun downloadMoviesSearchResult(text: String): Single<PageOfMovies> {
        return searchApi.getMoviesSearchResult(text)
    }

    override fun downloadTvShowsSearchResult(text: String): Single<PageOfTvShows> {
        return searchApi.getTvShowsSearchResult(text)
    }

    override fun downloadPeopleSearchResult(text: String): Single<PageOfPeople> {
        return searchApi.getPeopleSearchResult(text)
    }
}