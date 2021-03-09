package com.godelbrestandroid.popcornapp.ui.fragment.vertical.tvShows

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.tvShows.AiringTvShowsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.tvShows.AiringTvShowsView
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.BaseVerticalFragment

class AiringTvShowsFragment : BaseVerticalFragment<AiringTvShowsPresenter>(),
    AiringTvShowsView {

    companion object {
        fun newInstance(): AiringTvShowsFragment {
            val fragment = AiringTvShowsFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @ProvidePresenter
    fun providePresenter(): AiringTvShowsPresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: AiringTvShowsPresenter
}
