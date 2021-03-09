package com.godelbrestandroid.popcornapp.presentation.presenter.vertical.movies

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.repositories.MoviesRepository
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.movies.TopRatedMoviesView
import javax.inject.Inject

@InjectViewState
class TopRatedMoviesPresenter @Inject constructor(
    private val moviesRepository: MoviesRepository, private val shortInfoMapper: ShortInfoMapper
) : BaseVerticalPresenter<TopRatedMoviesView>() {
    override fun downloadPage(page: Int) {
        moviesRepository.getTopRatedMovies(page)
            .subscribeIoObserveMain().notifyLoadFinished()
            .doOnNext { viewState.updateAdapter(shortInfoMapper.pageOfMoviesToShortInfoList(it)) }
            .autoDisposableSubscribe()
    }
}
