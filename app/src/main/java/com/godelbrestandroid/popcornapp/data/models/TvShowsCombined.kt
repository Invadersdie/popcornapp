package com.godelbrestandroid.popcornapp.data.models

import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow

data class TvShowsCombined(
    val popularTvShows: List<TvShow>,
    val onTheAirTvShows: List<TvShow>,
    val topRatedTvShows: List<TvShow>,
    val airingTvShows: List<TvShow>
)