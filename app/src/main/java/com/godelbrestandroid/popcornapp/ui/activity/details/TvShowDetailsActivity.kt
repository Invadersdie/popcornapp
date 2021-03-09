package com.godelbrestandroid.popcornapp.ui.activity.details

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.internet.models.tvshows.TvShow
import com.godelbrestandroid.popcornapp.presentation.presenter.details.TvShowDetailsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.details.TvShowDetailsView
import kotlinx.android.synthetic.main.details_extend_tv_show.*


class TvShowDetailsActivity :
    BaseDetailsActivity<TvShowDetailsPresenter, TvShow>(R.layout.details_extend_tv_show),
    TvShowDetailsView {

    companion object {
        const val TAG = "TvShowDetailsActivity"
        fun getIntent(context: Context): Intent = Intent(context, TvShowDetailsActivity::class.java)
    }


    @InjectPresenter
    override lateinit var presenter: TvShowDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): TvShowDetailsPresenter = lazyPresenter.get()


    override fun updateView(item: TvShow) {
        tv_title.text = item.name
        tv_overview.text = item.overview
    }

}
