package com.godelbrestandroid.popcornapp.presentation.view

import com.godelbrestandroid.popcornapp.data.models.ShortInfo

interface SearchView : BaseView {
    fun updateAdapter(shortInfoList: List<ShortInfo>)
    fun suggestUseSearchAllCategories()
    fun showNoResult()
    fun hideNoResult()
}
