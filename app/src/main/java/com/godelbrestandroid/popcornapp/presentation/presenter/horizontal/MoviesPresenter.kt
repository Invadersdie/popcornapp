package com.godelbrestandroid.popcornapp.presentation.presenter.horizontal

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.ProviderCombinedResults
import com.godelbrestandroid.popcornapp.data.mappers.CombinedMapper
import com.godelbrestandroid.popcornapp.presentation.view.horizontal.movies.MoviesView
import javax.inject.Inject

@InjectViewState
class MoviesPresenter @Inject constructor(
    private val providerCombinedResults: ProviderCombinedResults,
    private val combinedMapper: CombinedMapper
) : BaseHorizontalPresenter<MoviesView>() {

    override fun initView() {
        providerCombinedResults.getMoviesCombined()
            .subscribeIoObserveMain()
            .doOnNext { viewState.updateAdapter(combinedMapper.moviesCombinedToCommonList(it)) }
            .autoDisposableSubscribe()
    }


}

