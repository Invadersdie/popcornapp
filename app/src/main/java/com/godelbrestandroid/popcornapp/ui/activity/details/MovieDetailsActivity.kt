package com.godelbrestandroid.popcornapp.ui.activity.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.adapters.DataRecyclerViewAdapter
import com.godelbrestandroid.popcornapp.adapters.ImagesAdapter
import com.godelbrestandroid.popcornapp.adapters.ReviewsAdapter
import com.godelbrestandroid.popcornapp.adapters.ViewHolderType
import com.godelbrestandroid.popcornapp.data.internet.models.Genre
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Backdrop
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Review
import com.godelbrestandroid.popcornapp.data.models.MovieDetailsCombined
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.presentation.presenter.details.MovieDetailsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.details.MovieDetailsView
import com.godelbrestandroid.popcornapp.ui.applyRecViewHorizontalSettings
import com.godelbrestandroid.popcornapp.ui.itemDecorators.ReviewDecorator
import kotlinx.android.synthetic.main.details_extend_movie.*


class MovieDetailsActivity :
    BaseDetailsActivity<MovieDetailsPresenter, MovieDetailsCombined>(R.layout.details_extend_movie),
    MovieDetailsView {


    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MovieDetailsActivity::class.java)
    }

    @InjectPresenter
    override lateinit var presenter: MovieDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): MovieDetailsPresenter = lazyPresenter.get()

    private lateinit var topBilledAdapter: DataRecyclerViewAdapter
    private lateinit var imagesAdapter: ImagesAdapter<Backdrop>
    private lateinit var recommendationAdapter: DataRecyclerViewAdapter
    private lateinit var reviewAdapter: ReviewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topBilledAdapter = DataRecyclerViewAdapter(ViewHolderType.HORIZONTAL, this, baseContext)
        imagesAdapter = ImagesAdapter()
        recommendationAdapter =
            DataRecyclerViewAdapter(ViewHolderType.HORIZONTAL_WITH_RATE, this, baseContext)
        reviewAdapter = ReviewsAdapter()

        movie_details_images.applyRecViewHorizontalSettings(imagesAdapter)
        movie_details_top_billed.applyRecViewHorizontalSettings(topBilledAdapter)
        movie_details_recommendations.applyRecViewHorizontalSettings(recommendationAdapter)
        movie_details_reviews.apply {
            val layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
            adapter = reviewAdapter
            this.layoutManager = layoutManager
            addItemDecoration(ReviewDecorator(this.context, layoutManager.orientation).apply {
                setDrawable(ContextCompat.getDrawable(this.context, android.R.color.transparent)!!)
            })
        }
        show_details.setOnClickListener { presenter.detailsClick() }
    }

    override fun updateView(item: MovieDetailsCombined) {
        movie_details_genre.text = genresListToString(item.movie.genres)
        movie_details_adult.text = when (item.movie.adult) {
            true -> "16+"
            false -> "0+"
        }
        movie_details_length.text = String.format("%d min", item.movie.runtime)
        movie_details_release_date.text = item.movie.release_date
        movie_details_popularity.text = item.movie.vote_average.toString()
        movie_details_popularity_votes.text = item.movie.vote_count.toString()
        movie_details_overview.text = item.movie.overview
        movie_details_tags_text.text = item.movie.tagline
    }


    override fun updateImagesAdapter(dataList: List<Backdrop>) {
        imagesAdapter.updateAdapter(dataList)
    }

    override fun updateTopBilledAdapter(dataList: List<ShortInfo>) {
        topBilledAdapter.updateAdapter(dataList)
    }

    override fun updateRecommendationAdapter(dataList: List<ShortInfo>) {
        recommendationAdapter.updateAdapter(dataList)
    }

    override fun updateReviewAdapter(dataList: List<Review>) {
        reviewAdapter.updateAdapter(dataList)
    }

    private fun genresListToString(genres: List<Genre>): String {
        val stringOfTitles = StringBuilder()
        for (genre in genres) {
            if (genre.name != "null") {
                stringOfTitles.append(genre.name).append(", ")
            }
        }
        if (stringOfTitles.isNotEmpty() && stringOfTitles.last() == ' ') {
            stringOfTitles.setLength(stringOfTitles.length - 2)
        }
        return stringOfTitles.toString()
    }
}