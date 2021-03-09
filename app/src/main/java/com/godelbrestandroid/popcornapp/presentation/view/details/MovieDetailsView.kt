package com.godelbrestandroid.popcornapp.presentation.view.details

import com.godelbrestandroid.popcornapp.data.internet.models.movies.Backdrop
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Review
import com.godelbrestandroid.popcornapp.data.models.MovieDetailsCombined
import com.godelbrestandroid.popcornapp.data.models.ShortInfo

interface MovieDetailsView : BaseDetailsView<MovieDetailsCombined> {
    fun updateImagesAdapter(dataList: List<Backdrop>)
    fun updateTopBilledAdapter(dataList: List<ShortInfo>)
    fun updateRecommendationAdapter(dataList: List<ShortInfo>)
    fun updateReviewAdapter(dataList: List<Review>)
}
