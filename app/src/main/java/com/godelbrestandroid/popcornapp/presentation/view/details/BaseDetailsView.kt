package com.godelbrestandroid.popcornapp.presentation.view.details

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType

interface BaseDetailsView<T> : MvpView {
    fun updateView(item: T)
    fun setMainImage(path: String?)
    fun setBlur(newBlur: Float)
    fun setTransparency(newTransparency: Int)
    fun showToolbar()
    fun hideToolbar()
    @StateStrategyType(SkipStrategy::class)
    fun openDetails(type: MediaType, id: Int)

    fun showToast(message: String)
    fun showMainView()

    fun showDetails()
    fun hideDetails()

    fun showLoading()
    fun hideLoading()
}