package com.godelbrestandroid.popcornapp.presentation.presenter

import com.arellomobile.mvp.MvpPresenter
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.presentation.view.BaseView
import io.reactivex.Observable

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


abstract class BasePresenter<View : BaseView> : MvpPresenter<View>() {

    private val compositeDisposable = CompositeDisposable()

    private fun dispose() {
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        dispose()
        super.onDestroy()
    }

    fun <T> Observable<T>.subscribeIoObserveMain(): Observable<T> {
        return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> Observable<T>.autoDisposableSubscribe() {
        doOnSubscribe { viewState.showLoading() }
            .doAfterNext { viewState.hideLoading() }
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun <T> Single<T>.subscribeIoObserveMain(): Single<T> {
        return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> Single<T>.autoDisposableSubscribe() {
        doOnSubscribe { viewState.showLoading() }
            .doFinally { viewState.hideLoading() }
            .subscribe()
            .addTo(compositeDisposable)
    }

    abstract fun initView()

    fun onItemClick(itemType: MediaType, id: Int) {
        viewState.openDetails(itemType, id)
    }

}

