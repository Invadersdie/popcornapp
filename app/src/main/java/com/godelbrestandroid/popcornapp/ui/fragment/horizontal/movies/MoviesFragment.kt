package com.godelbrestandroid.popcornapp.ui.fragment.horizontal.movies

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.horizontal.MoviesPresenter
import com.godelbrestandroid.popcornapp.presentation.view.horizontal.movies.MoviesView
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.BaseHorizontalFragment


class MoviesFragment : BaseHorizontalFragment<MoviesPresenter>(),
    MoviesView {

    companion object {
        fun newInstance(): MoviesFragment {
            val fragment = MoviesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    override lateinit var presenter: MoviesPresenter

    @ProvidePresenter
    fun providePresenter(): MoviesPresenter = lazyPresenter.get()

}
