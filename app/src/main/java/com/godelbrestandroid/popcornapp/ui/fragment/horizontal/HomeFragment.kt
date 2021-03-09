package com.godelbrestandroid.popcornapp.ui.fragment.horizontal

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.presentation.presenter.horizontal.HomePresenter
import com.godelbrestandroid.popcornapp.presentation.view.horizontal.HomeView

class HomeFragment : BaseHorizontalFragment<HomePresenter>(), HomeView {

    companion object {
        fun newInstance(): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    @InjectPresenter
    override lateinit var presenter: HomePresenter

    @ProvidePresenter
    fun providePresenter(): HomePresenter = lazyPresenter.get()



}

