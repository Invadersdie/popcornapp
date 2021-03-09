package com.godelbrestandroid.popcornapp.presentation.presenter.vertical.tvShows

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.repositories.TvShowsRepository
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.tvShows.OnTheAirTvShowsView
import javax.inject.Inject

@InjectViewState
class OnTheAirTvShowsPresenter @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
    private val shortInfoMapper: ShortInfoMapper
) : BaseVerticalPresenter<OnTheAirTvShowsView>() {
    override fun downloadPage(page: Int) {
        tvShowsRepository.downloadOnTheAirTvShows(page)
            .subscribeIoObserveMain().notifyLoadFinished()
            .doOnNext { viewState.updateAdapter(shortInfoMapper.pageOfTvShowsToShortInfoList(it)) }
            .autoDisposableSubscribe()
    }
}
