package com.godelbrestandroid.popcornapp.presentation.presenter.vertical.tvShows

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.repositories.TvShowsRepository
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.tvShows.AiringTvShowsView
import javax.inject.Inject

@InjectViewState
class AiringTvShowsPresenter @Inject constructor(
    private val tvShowsRepository: TvShowsRepository,
    private val shortInfoMapper: ShortInfoMapper
) :
    BaseVerticalPresenter<AiringTvShowsView>() {
    override fun downloadPage(page: Int) {
        tvShowsRepository.downloadAiringTvShows(page)
            .subscribeIoObserveMain()
            .notifyLoadFinished()
            .doOnNext { viewState.updateAdapter(shortInfoMapper.pageOfTvShowsToShortInfoList(it)) }
            .autoDisposableSubscribe()
    }
}
