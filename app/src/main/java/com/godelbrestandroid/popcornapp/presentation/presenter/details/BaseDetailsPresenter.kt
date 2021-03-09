package com.godelbrestandroid.popcornapp.presentation.presenter.details

import com.arellomobile.mvp.MvpPresenter
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.presentation.view.details.BaseDetailsView
import com.godelbrestandroid.popcornapp.ui.activity.details.MAX_BLUR
import com.godelbrestandroid.popcornapp.ui.activity.details.MAX_TRANSPARENCY
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

const val SCROLL_FINISH = 700

abstract class BaseDetailsPresenter<view : BaseDetailsView<*>> : MvpPresenter<view>() {

    private val compositeDisposable = CompositeDisposable()
    private var maxScroll = false
    private var toolbarShown = true

    private fun dispose() {
        compositeDisposable.clear()
    }

    fun onItemClick(itemType: MediaType, id: Int) {
        viewState.openDetails(itemType, id)
    }

    override fun onDestroy() {
        dispose()
        super.onDestroy()
    }

    fun <T> Observable<T>.subscribeIoObserveMain(): Observable<T> {
        return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }
    fun <T> Single<T>.subscribeIoObserveMain(): Single<T> {
        return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun <T> Observable<T>.autoDisposableSubscribe() {
        doOnSubscribe { viewState.showLoading() }.doAfterNext { viewState.hideLoading() }
            .doOnError { it.message?.let { message -> viewState.showToast(message) } }.subscribe()
            .addTo(compositeDisposable)
    }
    fun <T> Single<T>.autoDisposableSubscribe() {
        doOnSubscribe { viewState.showLoading() }.doFinally { viewState.hideLoading() }
            .doOnError { it.message?.let { message -> viewState.showToast(message) } }.subscribe()
            .addTo(compositeDisposable)
    }

    abstract fun initView(id: Int)

    fun onScrollChange(scrollY: Int, oldScrollY: Int) {
        if (oldScrollY > scrollY) {
            showToolbar()
        } else {
            hideToolbar()
        }
        when {
            scrollY == 0 -> {
                viewState.setBlur(0f)
                viewState.setTransparency(0)
            }
            scrollY < SCROLL_FINISH -> {
                val newTransparency = scrollY / SCROLL_FINISH.toDouble() * MAX_TRANSPARENCY
                val newBlur = scrollY / SCROLL_FINISH.toFloat() * MAX_BLUR
                viewState.setBlur(newBlur)
                viewState.setTransparency(newTransparency.toInt())
                maxScroll = false
            }
            else -> {
                if (!maxScroll) {
                    viewState.setTransparency(MAX_TRANSPARENCY)
                    viewState.setBlur(MAX_BLUR)
                    maxScroll = true
                }
            }
        }
    }

    private fun hideToolbar() {
        if (toolbarShown) {
            viewState.hideToolbar()
            toolbarShown = false
        }
    }

    private fun showToolbar() {
        if (!toolbarShown) {
            viewState.showToolbar()
            toolbarShown = true
        }
    }

    var detailsExpanded = false
    fun detailsClick() {
        detailsExpanded = detailsExpanded.not()
        if (detailsExpanded) {
            viewState.showDetails()
        } else {
            viewState.hideDetails()
        }
    }
}