package com.godelbrestandroid.popcornapp.presentation.presenter.horizontal

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.ProviderCombinedResults
import com.godelbrestandroid.popcornapp.data.mappers.CombinedMapper
import com.godelbrestandroid.popcornapp.presentation.view.horizontal.tvShows.TvShowsView
import javax.inject.Inject

@InjectViewState
class TvShowsPresenter @Inject constructor(
    private val providerCombinedResults: ProviderCombinedResults,
    private val combinedMapper: CombinedMapper
) : BaseHorizontalPresenter<TvShowsView>() {


    override fun initView() {
        providerCombinedResults.getTvShowsCombined()
            .subscribeIoObserveMain()
            .doOnNext {
                viewState.updateAdapter(combinedMapper.tvShowsCombinedToCommonList(it))
            }
            .autoDisposableSubscribe()
    }

}
