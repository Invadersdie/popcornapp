package com.godelbrestandroid.popcornapp.data.repositories

import com.godelbrestandroid.popcornapp.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

abstract class BaseRepository(private val networkUtils: NetworkUtils) {

    protected fun <T> combine(dbRequest: Observable<T>, netRequest: Single<*>)
            : Observable<T> {
        return if (networkUtils.isNetworkAvailable()) {
            return Observable.combineLatest(
                dbRequest,
                netRequest.toObservable(),
                BiFunction { dbData, _ -> dbData })
        } else {
            dbRequest
        }
    }

    protected fun <T> combineList(dbRequest: Observable<List<T>>, netRequest: Single<*>)
            : Observable<List<T>> {
        return if (networkUtils.isNetworkAvailable()) {
            Observable.combineLatest(
                dbRequest,
                netRequest.toObservable(),
                BiFunction { dbData, _ -> dbData })
        } else {
            dbRequest
        }
    }

}