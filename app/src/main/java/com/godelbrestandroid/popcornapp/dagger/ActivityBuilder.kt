package com.godelbrestandroid.popcornapp.dagger


import com.godelbrestandroid.popcornapp.ui.activity.MainActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.MovieDetailsActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.PeopleDetailsActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.TvShowDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindMovieDetailsActivity(): MovieDetailsActivity

    @ContributesAndroidInjector
    abstract fun bindTvShowDetailsActivity(): TvShowDetailsActivity

    @ContributesAndroidInjector
    abstract fun bindPeopleDetailsActivity(): PeopleDetailsActivity

}