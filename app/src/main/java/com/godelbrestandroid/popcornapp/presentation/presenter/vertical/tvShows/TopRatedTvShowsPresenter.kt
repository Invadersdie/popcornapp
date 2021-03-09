package com.godelbrestandroid.popcornapp.presentation.presenter.vertical.tvShows

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.repositories.TvShowsRepository
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.tvShows.TopRatedTvShowsView
import javax.inject.Inject

@InjectViewState
class TopRatedTvShowsPresenter @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
    private val shortInfoMapper: ShortInfoMapper
) : BaseVerticalPresenter<TopRatedTvShowsView>() {
    override fun downloadPage(page: Int) {
        tvShowsRepository.downloadTopRatedTvShows(page)
            .subscribeIoObserveMain().notifyLoadFinished()
            .doOnNext { viewState.updateAdapter(shortInfoMapper.pageOfTvShowsToShortInfoList(it)) }
            .autoDisposableSubscribe()
    }
}