package com.godelbrestandroid.popcornapp.data

import com.godelbrestandroid.popcornapp.data.models.HomeCombined
import com.godelbrestandroid.popcornapp.data.models.MovieDetailsCombined
import com.godelbrestandroid.popcornapp.data.models.MoviesCombined
import com.godelbrestandroid.popcornapp.data.models.TvShowsCombined
import io.reactivex.Observable

interface ProviderCombinedResults {
    fun getHomeCombinedResult(): Observable<HomeCombined>
    fun getMoviesCombined(): Observable<MoviesCombined>
    fun getTvShowsCombined(): Observable<TvShowsCombined>
    fun getMovieDetailsCombined(id: Int): Observable<MovieDetailsCombined>
}