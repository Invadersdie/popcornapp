package com.godelbrestandroid.popcornapp.presentation.presenter.vertical.movies

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.repositories.MoviesRepository
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.movies.PopularMovieView
import javax.inject.Inject

@InjectViewState
class PopularMoviePresenter @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val shortInfoMapper: ShortInfoMapper
) :
    BaseVerticalPresenter<PopularMovieView>() {
    override fun downloadPage(page: Int) {
        moviesRepository.getPopularMovies(page)
            .subscribeIoObserveMain().notifyLoadFinished()
            .doOnNext {
                viewState.updateAdapter(shortInfoMapper.pageOfMoviesToShortInfoList(it))
            }
            .autoDisposableSubscribe()
    }
}
