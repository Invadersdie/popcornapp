package com.godelbrestandroid.popcornapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.adapters.SearchAdapter
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.presentation.presenter.SearchMode
import com.godelbrestandroid.popcornapp.presentation.presenter.SearchPresenter
import com.godelbrestandroid.popcornapp.presentation.view.SearchView
import com.godelbrestandroid.popcornapp.ui.activity.MainActivity
import com.godelbrestandroid.popcornapp.ui.itemDecorators.SearchItemDecorator
import kotlinx.android.synthetic.main.fragment_search.*

private const val SEARCH_MODE = "search_mode"

class SearchFragment : BaseFragment<SearchPresenter>(), SearchView {


    companion object {
        fun newInstance(searchViewMode: SearchMode): SearchFragment {
            val fragment = SearchFragment()
            val args = Bundle()
            args.putSerializable(SEARCH_MODE, searchViewMode)
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var searchAdapter: SearchAdapter

    @InjectPresenter
    override lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter(): SearchPresenter = lazyPresenter.get()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = this.arguments
        if (bundle != null) {
            presenter.setMode(bundle.getSerializable(SEARCH_MODE) as SearchMode)
        }
        searchAdapter = SearchAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).disableDrawerLayout()
        val layoutManager =
            LinearLayoutManager(this@SearchFragment.context, RecyclerView.VERTICAL, false)
        search_recycler_view.apply {
            this.layoutManager = layoutManager
            adapter = searchAdapter
            addItemDecoration(SearchItemDecorator(context, layoutManager.orientation))
        }
        search_try_all_mode.setOnClickListener {
            presenter.setMode(SearchMode.ALL)
            presenter.applySearchQuery()
        }
    }


    override fun updateAdapter(shortInfoList: List<ShortInfo>) {
        searchAdapter.updateAdapter(shortInfoList)
    }

    override fun suggestUseSearchAllCategories() {
        showTryAllSearch()
        showNoResult()
    }

    private fun showTryAllSearch() {
        search_try_all_mode.visibility = View.VISIBLE
    }

    private fun hideTryAllSearch() {
        search_try_all_mode.visibility = View.GONE
    }

    override fun hideNoResult() {
        search_no_result.visibility = View.GONE
        hideTryAllSearch()
    }

    override fun showNoResult() {
        search_no_result.visibility = View.VISIBLE
    }

    fun applySearchQuery(newText: String?) {
        presenter.applySearchQuery(newText)
    }
}
