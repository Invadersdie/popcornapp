package com.godelbrestandroid.popcornapp.ui.fragment

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.MvpAppCompatFragment
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.presentation.presenter.BasePresenter
import com.godelbrestandroid.popcornapp.presentation.view.BaseView
import com.godelbrestandroid.popcornapp.ui.Clickable
import com.godelbrestandroid.popcornapp.ui.activity.MainActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.MovieDetailsActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.PeopleDetailsActivity
import com.godelbrestandroid.popcornapp.ui.activity.details.TvShowDetailsActivity
import com.godelbrestandroid.popcornapp.utils.StringUtils
import dagger.Lazy
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment<P : BasePresenter<*>> : MvpAppCompatFragment(), BaseView, Clickable {

    abstract var presenter: P
    @Inject
    lateinit var lazyPresenter: Lazy<P>

    override fun onItemClick(itemType: MediaType, id: Int) {
        presenter.onItemClick(itemType, id)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        (activity as MainActivity).hideLoading()
    }

    override fun showLoading() {
        (activity as MainActivity).showLoading()
    }

    override fun openDetails(type: MediaType, id: Int) {
        startActivity(
            when (type) {
                MediaType.movie -> MovieDetailsActivity.getIntent(context!!)
                MediaType.tv -> TvShowDetailsActivity.getIntent(context!!)
                MediaType.person -> PeopleDetailsActivity.getIntent(context!!)
            }.apply { putExtra(StringUtils.ID, id) })
    }

    override fun openFragment(fragment: Fragment) {
        (activity as MainActivity).replaceFragment(fragment, true)
    }
}
