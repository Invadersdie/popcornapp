package com.godelbrestandroid.popcornapp.presentation.view.horizontal

import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.godelbrestandroid.popcornapp.data.models.CommonScreenItem
import com.godelbrestandroid.popcornapp.presentation.view.BaseView

interface BaseHorizontalView : BaseView {
    @StateStrategyType(SkipStrategy::class)
    fun updateAdapter(commonScreenItemList: List<CommonScreenItem>)

}