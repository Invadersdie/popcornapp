package com.godelbrestandroid.popcornapp.dagger

import com.godelbrestandroid.popcornapp.ui.fragment.SearchFragment
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.HomeFragment
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.movies.MoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.tvShows.TvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.NowPlayingMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.PopularMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.TopRatedMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies.UpcomingMoviesFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.people.PopularPeopleFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.AiringTvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.OnTheAirTvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.PopularTvShowsFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows.TopRatedTvShowsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {

//    @ContributesAndroidInjector
//    abstract fun bindBaseHorizontalFragment(): BaseHorizontalFragment


    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): SearchFragment

    @ContributesAndroidInjector
    abstract fun bindPopularPeopleFragment(): PopularPeopleFragment

    @ContributesAndroidInjector
    abstract fun bindMoviesFragment(): MoviesFragment

    @ContributesAndroidInjector
    abstract fun bindPopularMoviesFragment(): PopularMoviesFragment
    @ContributesAndroidInjector
    abstract fun bindNowPlayingMoviesFragment(): NowPlayingMoviesFragment
    @ContributesAndroidInjector
    abstract fun bindTopRatedMoviesFragment(): TopRatedMoviesFragment
    @ContributesAndroidInjector
    abstract fun bindUpcomingMoviesFragment(): UpcomingMoviesFragment



    @ContributesAndroidInjector
    abstract fun bindTvShowsFragment(): TvShowsFragment

    @ContributesAndroidInjector
    abstract fun bindPopularTvShowsFragment(): PopularTvShowsFragment

    @ContributesAndroidInjector
    abstract fun bindAiringTvShowsFragment(): AiringTvShowsFragment

    @ContributesAndroidInjector
    abstract fun bindOnTheAirTvShowsFragment(): OnTheAirTvShowsFragment

    @ContributesAndroidInjector
    abstract fun bindTopRatedTvShowsFragment(): TopRatedTvShowsFragment

}