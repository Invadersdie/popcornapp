package com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.tvShows.PopularTvShowsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.tvShows.PopularTvShowsView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class PopularTvShowsFragment : BaseVerticalFragment<PopularTvShowsPresenter>(), PopularTvShowsView {

    companion object {
        fun newInstance(): PopularTvShowsFragment {
            val fragment = PopularTvShowsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }


    @ProvidePresenter
    fun providePresenter(): PopularTvShowsPresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: PopularTvShowsPresenter
}
