package com.godelbrestandroid.popcornapp.presentation.view.vertical

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.presentation.view.BaseView

interface BaseVerticalView : BaseView {
    @StateStrategyType(SkipStrategy::class)
    fun updateAdapter(shortInfoList: List<ShortInfo>)

    fun finishLoading()
    fun startLoading()
}