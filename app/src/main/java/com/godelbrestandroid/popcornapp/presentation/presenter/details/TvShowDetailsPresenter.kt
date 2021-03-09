package com.godelbrestandroid.popcornapp.presentation.presenter.details

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.repositories.TvShowsRepository
import com.godelbrestandroid.popcornapp.presentation.view.details.TvShowDetailsView
import javax.inject.Inject

@InjectViewState
class TvShowDetailsPresenter @Inject constructor(private val tvShowsRepository: TvShowsRepository) :
    BaseDetailsPresenter<TvShowDetailsView>() {
    override fun initView(id: Int) {
        tvShowsRepository.downloadTvShowDetails(id)
            .subscribeIoObserveMain()
            .doOnNext {
                viewState.updateView(it)
                viewState.setMainImage(it.poster_path)
            }
            .autoDisposableSubscribe()
    }
}
