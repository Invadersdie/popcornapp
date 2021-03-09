package com.godelbrestandroid.popcornapp.presentation.presenter.vertical.movies

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.repositories.MoviesRepository
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.movies.NowPlayingMoviesView
import javax.inject.Inject

@InjectViewState
class NowPlayingMoviesPresenter @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val shortInfoMapper: ShortInfoMapper
) : BaseVerticalPresenter<NowPlayingMoviesView>() {
    override fun downloadPage(page: Int) {
        moviesRepository.getNowPlayingMovies(page)
            .subscribeIoObserveMain().notifyLoadFinished()
            .doOnNext { viewState.updateAdapter(shortInfoMapper.pageOfMoviesToShortInfoList(it)) }
            .autoDisposableSubscribe()
    }
}
