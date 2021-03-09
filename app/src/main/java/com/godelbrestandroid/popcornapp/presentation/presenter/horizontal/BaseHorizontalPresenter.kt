package com.godelbrestandroid.popcornapp.presentation.presenter.horizontal


import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.presentation.presenter.BasePresenter
import com.godelbrestandroid.popcornapp.presentation.view.horizontal.BaseHorizontalView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.NowPlayingMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.PopularMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.TopRatedMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.UpcomingMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.people.PopularPeopleFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.AiringTvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.OnTheAirTvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.PopularTvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.TopRatedTvShowsFragment
import com.godelbrestandroid.popcornapp.utils.Types


abstract class BaseHorizontalPresenter<view : BaseHorizontalView> : BasePresenter<view>() {
    fun onMoreClick(groupName: String, dataType: MediaType) {
        when (dataType) {
            MediaType.movie -> when (groupName) {
                Types.MOVIE, Types.MOVIE_POPULAR -> viewState.openFragment(PopularMoviesFragment())
                Types.MOVIE_NOW_PLAYING -> viewState.openFragment(NowPlayingMoviesFragment())
                Types.MOVIE_TOP_RATED -> viewState.openFragment(TopRatedMoviesFragment())
                Types.MOVIE_UPCOMING -> viewState.openFragment(UpcomingMoviesFragment())
                else -> throw IllegalStateException()
            }
            MediaType.tv -> when (groupName) {
                Types.TVSHOW, Types.TVSHOW_POPULAR -> viewState.openFragment(PopularTvShowsFragment())
                Types.TVSHOW_AIRING_TODAY -> viewState.openFragment(AiringTvShowsFragment())
                Types.TVSHOW_ON_TV -> viewState.openFragment(OnTheAirTvShowsFragment())
                Types.TVSHOW_TOP_RATED -> viewState.openFragment(TopRatedTvShowsFragment())
            }
            MediaType.person -> viewState.openFragment(PopularPeopleFragment())
        }
    }
}
