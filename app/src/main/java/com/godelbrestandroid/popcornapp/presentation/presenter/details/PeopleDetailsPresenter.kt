package com.godelbrestandroid.popcornapp.presentation.presenter.details

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.repositories.PeopleRepository
import com.godelbrestandroid.popcornapp.presentation.view.details.PeopleDetailsView
import javax.inject.Inject

@InjectViewState
class PeopleDetailsPresenter @Inject constructor(private val peopleRepository: PeopleRepository) :
    BaseDetailsPresenter<PeopleDetailsView>() {
    override fun initView(id: Int) {
        peopleRepository.getDetailsPerson(id)
            .subscribeIoObserveMain()
            .doOnNext {
                viewState.updateView(it)
                viewState.setMainImage(it.profile_path)
            }
            .autoDisposableSubscribe()
    }
}
