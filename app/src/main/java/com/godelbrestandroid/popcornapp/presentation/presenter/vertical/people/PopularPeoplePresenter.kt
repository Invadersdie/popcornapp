package com.godelbrestandroid.popcornapp.presentation.presenter.vertical.people

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.repositories.PeopleRepository
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.people.PopularPeopleView
import javax.inject.Inject

@InjectViewState
class PopularPeoplePresenter @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val shortInfoMapper: ShortInfoMapper
) : BaseVerticalPresenter<PopularPeopleView>() {
    override fun downloadPage(page: Int) {
        peopleRepository.getPopularPeople(page)
            .subscribeIoObserveMain().notifyLoadFinished()
            .doOnNext {
                viewState.updateAdapter(shortInfoMapper.pageOfPeopleToShortInfoList(it))
            }
            .autoDisposableSubscribe()
    }
}