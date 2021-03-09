package com.godelbrestandroid.popcornapp.ui.fragment.horizontal.tvShows

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.horizontal.TvShowsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.horizontal.tvShows.TvShowsView
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.BaseHorizontalFragment

class TvShowsFragment : BaseHorizontalFragment<TvShowsPresenter>(),
    TvShowsView {


    companion object {
        fun newInstance(): TvShowsFragment {
            val fragment = TvShowsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    @InjectPresenter
    override lateinit var presenter: TvShowsPresenter

    @ProvidePresenter
    fun providePresenter(): TvShowsPresenter = lazyPresenter.get()
}
