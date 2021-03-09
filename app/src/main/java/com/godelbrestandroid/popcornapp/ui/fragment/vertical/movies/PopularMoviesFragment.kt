package com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies


import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.movies.PopularMoviePresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.movies.PopularMovieView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class PopularMoviesFragment : BaseVerticalFragment<PopularMoviePresenter>(), PopularMovieView {

    companion object {
        fun newInstance(): PopularMoviesFragment {
            val fragment = PopularMoviesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @ProvidePresenter
    fun providePresenter(): PopularMoviePresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: PopularMoviePresenter
}
