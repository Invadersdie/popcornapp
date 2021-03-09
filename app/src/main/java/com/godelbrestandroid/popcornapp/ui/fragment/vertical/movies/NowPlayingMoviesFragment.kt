package com.godelbrestandroid.popcornapp.ui.fragment.vertical.movies

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.movies.NowPlayingMoviesPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.movies.NowPlayingMoviesView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class NowPlayingMoviesFragment : BaseVerticalFragment<NowPlayingMoviesPresenter>(),
    NowPlayingMoviesView {

    companion object {
        fun newInstance(): NowPlayingMoviesFragment {
            val fragment = NowPlayingMoviesFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @ProvidePresenter
    fun providePresenter(): NowPlayingMoviesPresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: NowPlayingMoviesPresenter
}
