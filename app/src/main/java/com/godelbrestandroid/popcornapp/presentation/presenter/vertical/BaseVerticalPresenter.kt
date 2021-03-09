package com.godelbrestandroid.popcornapp.presentation.presenter.vertical

import com.godelbrestandroid.popcornapp.presentation.presenter.BasePresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.BaseVerticalView
import io.reactivex.Observable
import io.reactivex.Single

const val START_PAGE = 1

abstract class BaseVerticalPresenter<view : BaseVerticalView> :
    BasePresenter<view>() {

    var page = START_PAGE
    override fun initView() {
        downloadPage(page)
    }

    fun downloadNextPage() {
        viewState.startLoading()
        downloadPage(++page)
    }

    fun <T> Observable<T>.notifyLoadFinished(): Observable<T> {
        return doAfterNext { viewState.finishLoading() }
    }
    fun <T> Single<T>.notifyLoadFinished(): Single<T> {
        return doFinally { viewState.finishLoading() }
    }
    abstract fun downloadPage(page: Int)
}