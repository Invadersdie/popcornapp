package com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.movies.UpcomingMoviesPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.movies.UpcomingMoviesView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class UpcomingMoviesFragment : BaseVerticalFragment<UpcomingMoviesPresenter>(), UpcomingMoviesView {

    companion object {
        fun newInstance(): UpcomingMoviesFragment {
            val fragment =
                UpcomingMoviesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @ProvidePresenter
    fun providePresenter(): UpcomingMoviesPresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: UpcomingMoviesPresenter

}
