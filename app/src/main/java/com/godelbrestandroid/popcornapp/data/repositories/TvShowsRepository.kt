package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow
import io.reactivex.Observable
interface TvShowsRepository {

    fun downloadPopularTvShows(page: Int = 1): Observable<List<TvShow>>
    fun downloadAiringTvShows(page: Int = 1): Observable<List<TvShow>>
    fun downloadOnTheAirTvShows(page: Int = 1): Observable<List<TvShow>>
    fun downloadTopRatedTvShows(page: Int = 1): Observable<List<TvShow>>
    fun downloadTvShowDetails(id: Int): Observable<TvShow>
}