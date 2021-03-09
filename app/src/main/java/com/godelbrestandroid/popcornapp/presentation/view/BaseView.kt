package com.godelbrestandroid.popcornapp.presentation.view

import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType

interface BaseView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showMessage(message: String)

    @StateStrategyType(SkipStrategy::class)
    fun hideLoading()

    @StateStrategyType(SkipStrategy::class)
    fun showLoading()

    @StateStrategyType(SkipStrategy::class)
    fun openDetails(type: MediaType, id: Int)

    @StateStrategyType(SkipStrategy::class)
    fun openFragment(fragment: Fragment)
}