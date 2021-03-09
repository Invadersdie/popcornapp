package com.godelbrestandroid.popcornapp.ui.activity.details

import android.content.Context
import android.content.Intent
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.internet.models.people.Person
import com.godelbrestandroid.popcornapp.presentation.presenter.details.PeopleDetailsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.details.PeopleDetailsView
import kotlinx.android.synthetic.main.details_extend_people.*


class PeopleDetailsActivity :
    BaseDetailsActivity<PeopleDetailsPresenter, Person>(R.layout.details_extend_people),
    PeopleDetailsView {

    companion object {
        const val TAG = "PeopleDetailsActivity"
        fun getIntent(context: Context): Intent = Intent(context, PeopleDetailsActivity::class.java)
    }

    @InjectPresenter
    override lateinit var presenter: PeopleDetailsPresenter

    @ProvidePresenter
    fun providePresenter(): PeopleDetailsPresenter = lazyPresenter.get()


    override fun updateView(item: Person) {
        people_title.text = item.name
        people_overview.text = item.biography
    }
}
