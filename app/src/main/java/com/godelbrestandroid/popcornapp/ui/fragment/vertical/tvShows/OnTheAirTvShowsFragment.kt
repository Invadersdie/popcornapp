package com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.tvShows.OnTheAirTvShowsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.tvShows.OnTheAirTvShowsView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class OnTheAirTvShowsFragment : BaseVerticalFragment<OnTheAirTvShowsPresenter>(),
    OnTheAirTvShowsView {

    companion object {
        fun newInstance(): OnTheAirTvShowsFragment {
            val fragment =
                OnTheAirTvShowsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    @ProvidePresenter
    fun providePresenter(): OnTheAirTvShowsPresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: OnTheAirTvShowsPresenter

}
