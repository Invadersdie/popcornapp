package com.godelbrestandroid.popcornapp.presentation.presenter.horizontal

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.ProviderCombinedResults
import com.godelbrestandroid.popcornapp.data.mappers.CombinedMapper
import com.godelbrestandroid.popcornapp.presentation.view.horizontal.HomeView
import javax.inject.Inject

@InjectViewState
class HomePresenter @Inject constructor(
    private val providerCombinedResults: ProviderCombinedResults,
    private val combinedMapper: CombinedMapper
) : BaseHorizontalPresenter<HomeView>() {

    override fun initView() {
        providerCombinedResults.getHomeCombinedResult()
            .subscribeIoObserveMain()
            .doOnNext {
                viewState.updateAdapter(
                    combinedMapper.homeCombinedToCommonList(it)
                )
            }
            .autoDisposableSubscribe()
    }



}

