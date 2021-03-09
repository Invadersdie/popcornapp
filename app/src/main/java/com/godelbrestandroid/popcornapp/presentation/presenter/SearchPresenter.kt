package com.godelbrestandroid.popcornapp.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.godelbrestandroid.popcornapp.data.mappers.ShortInfoMapper
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.data.repositories.SearchRepository
import com.godelbrestandroid.popcornapp.presentation.view.SearchView
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val searchRepository: SearchRepository,
    private val shortInfoMapper: ShortInfoMapper
) : BasePresenter<SearchView>() {

    override fun initView() {
    }

    private var searchQuery: String? = null
    private var searchMode: SearchMode =
        SearchMode.ALL
    fun setMode(mode: SearchMode) {
        searchMode = mode
    }

    fun applySearchQuery(text: String? = searchQuery) {
        viewState.hideNoResult()
        searchQuery = text
        if (!text.isNullOrEmpty()) {
            when (searchMode) {
                SearchMode.MOVIE -> searchRepository.downloadMoviesSearchResult(text)
                    .subscribeIoObserveMain()
                    .doOnSuccess {
                        updateAdapterOnDownload(shortInfoMapper.pageOfMoviesToShortInfoList(it.moviesList))
                    }
                    .autoDisposableSubscribe()
                SearchMode.TV_SHOWS -> searchRepository.downloadTvShowsSearchResult(text)
                    .subscribeIoObserveMain()
                    .doOnSuccess {
                        updateAdapterOnDownload(shortInfoMapper.pageOfTvShowsToShortInfoList(it.tvShowsList))
                    }
                    .autoDisposableSubscribe()
                SearchMode.PEOPLE -> searchRepository.downloadPeopleSearchResult(text)
                    .subscribeIoObserveMain().doOnSuccess {
                        updateAdapterOnDownload(shortInfoMapper.pageOfPeopleToShortInfoList(it.popularPeople))
                    }
                    .autoDisposableSubscribe()
                SearchMode.ALL -> searchRepository.downloadMultiSearchResult(text)
                    .subscribeIoObserveMain()
                    .doOnSuccess {
                        updateAdapterOnDownload(shortInfoMapper.multiSearchResultToShortInfoList(it))
                    }.autoDisposableSubscribe()
            }
        } else {
            viewState.updateAdapter(emptyList())
        }
    }

    private fun updateAdapterOnDownload(shortInfoList: List<ShortInfo>) {
        if (shortInfoList.isEmpty()) {
            viewState.updateAdapter(emptyList())
            if (searchMode != SearchMode.ALL) {
                viewState.suggestUseSearchAllCategories()
            } else {
                viewState.showNoResult()
            }
        } else {
            viewState.updateAdapter(shortInfoList)
        }
    }
}

enum class SearchMode {
    MOVIE,
    TV_SHOWS,
    PEOPLE,
    ALL
}