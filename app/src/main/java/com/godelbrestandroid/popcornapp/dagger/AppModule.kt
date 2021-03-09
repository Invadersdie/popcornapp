package com.godelbrestandroid.popcornapp.dagger

import com.godelbrestandroid.popcornapp.dagger.scopes.ActivityScope
import com.godelbrestandroid.popcornapp.dagger.scopes.FragmentScope
import com.godelbrestandroid.popcornapp.data.ProviderCombinedResults
import com.godelbrestandroid.popcornapp.data.ProviderCombinedResultsImpl
import com.godelbrestandroid.popcornapp.data.mappers.CombinedMapper
import com.godelbrestandroid.popcornapp.data.mappers.CombinedMapperImpl
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapperImpl
import com.godelbrestandroid.popcornapp.data.repositories.*
import com.godelbrestandroid.popcornapp.ui.activity.MainActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.MovieDetailsActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.PeopleDetailsActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.TvShowDetailsActivity
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
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector


@Module(includes = [AndroidInjectionModule::class])
interface AppModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun mainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun movieDetailsActivityInjector(): MovieDetailsActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun tvShowDetailsInjector(): TvShowDetailsActivity

    @ActivityScope
    @ContributesAndroidInjector
    fun peopleDetailsActivityInjector(): PeopleDetailsActivity



    @FragmentScope
    @ContributesAndroidInjector
    fun homeFragmentInjector(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun popularPeopleFragmentInjector(): PopularPeopleFragment


    @FragmentScope
    @ContributesAndroidInjector
    fun tvShowsFragmentInjector(): TvShowsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun popularTvShowsFragmentInjector(): PopularTvShowsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun airingTvShowsFragmentInjector(): AiringTvShowsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun onTheAirTvShowsFragmentInjector(): OnTheAirTvShowsFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun topRatedTvShowsFragmentInjector(): TopRatedTvShowsFragment


    @FragmentScope
    @ContributesAndroidInjector
    fun moviesFragmentInjector(): MoviesFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun popularMoviesFragmentInjector(): PopularMoviesFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun nowPlayingMoviesFragmentInjector(): NowPlayingMoviesFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun topRatedMoviesFragmentInjector(): TopRatedMoviesFragment

    @FragmentScope
    @ContributesAndroidInjector
    fun upcomingMoviesFragmentInjector(): UpcomingMoviesFragment


    @FragmentScope
    @ContributesAndroidInjector
    fun searchFragmentInjector(): SearchFragment


    @Binds
    fun providePeopleRepository(peopleRepository: PeopleRepositoryImpl): PeopleRepository

    @Binds
    fun provideMoviesRepository(moviesRepository: MoviesRepositoryImpl): MoviesRepository

    @Binds
    fun provideTvShowsRepository(tvShowsRepository: TvShowsRepositoryImpl): TvShowsRepository

    @Binds
    fun provideCommonRepository(commonRepositoryImpl: SearchRepositoryImpl): SearchRepository

    @Binds
    fun provideShortInfoMapper(shortInfoMapperImpl: ShortInfoMapperImpl): ShortInfoMapper

    @Binds
    fun provideCombinedMapper(combinedMapperImpl: CombinedMapperImpl): CombinedMapper

    @Binds
    fun provideProviderCombinedResults(providerCombinedResultsImpl: ProviderCombinedResultsImpl): ProviderCombinedResults

}