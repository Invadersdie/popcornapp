package com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.movies.TopRatedMoviesPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.movies.TopRatedMoviesView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class TopRatedMoviesFragment : BaseVerticalFragment<TopRatedMoviesPresenter>(), TopRatedMoviesView {


    companion object {
        fun newInstance(): TopRatedMoviesFragment {
            val fragment = TopRatedMoviesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @ProvidePresenter
    fun providePresenter(): TopRatedMoviesPresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: TopRatedMoviesPresenter
}
