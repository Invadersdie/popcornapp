package com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows


import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.tvShows.TopRatedTvShowsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.tvShows.TopRatedTvShowsView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class TopRatedTvShowsFragment : BaseVerticalFragment<TopRatedTvShowsPresenter>(),
    TopRatedTvShowsView {
    companion object {
        fun newInstance(): TopRatedTvShowsFragment {
            val fragment =
                TopRatedTvShowsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @ProvidePresenter
    fun providePresenter(): TopRatedTvShowsPresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: TopRatedTvShowsPresenter

}
