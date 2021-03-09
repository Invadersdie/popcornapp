package com.godelbrestandroid.popcornapp.presentation.presenter.details

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.ProviderCombinedResults
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.presentation.view.details.MovieDetailsView
import javax.inject.Inject

@InjectViewState
class MovieDetailsPresenter @Inject constructor(
    private val providerCombinedResults: ProviderCombinedResults,
    private val shortInfoMapper: ShortInfoMapper
) : BaseDetailsPresenter<MovieDetailsView>() {

    override fun initView(id: Int) {
        providerCombinedResults.getMovieDetailsCombined(id)
            .subscribeIoObserveMain()
            .doOnNext {
                viewState.updateView(it)
                viewState.updateImagesAdapter(it.movieImages.backdrops)
                viewState.updateRecommendationAdapter(
                    shortInfoMapper.pageOfMoviesWithRateToShortInfoList(
                        it.movieRecommendations
                    )
                )
                viewState.updateReviewAdapter(it.movieReviews)
                viewState.updateTopBilledAdapter(
                    shortInfoMapper.movieCreditsToShortList(it.movieCredits)
                )
                viewState.setMainImage(it.movie.poster_path)

            }
            .doAfterNext { viewState.showMainView() }
            .autoDisposableSubscribe()
    }
}