package com.godelbrestandroid.popcornapp.ui.fragment.vertical

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.adapters.DataRecyclerViewAdapter
import com.godelbrestandroid.popcornapp.adapters.ViewHolderType
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.BaseVerticalPresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.BaseVerticalView
import com.godelbrestandroid.popcornapp.ui.activity.MainActivity
import com.godelbrestandroid.popcornapp.ui.fragment.BaseFragment
import com.godelbrestandroid.popcornapp.ui.itemDecorators.VerticalItemDecorator
import kotlinx.android.synthetic.main.fragment_vertical.*

const val THRESHOLD = 2

abstract class BaseVerticalFragment<P : BaseVerticalPresenter<*>> : BaseFragment<P>(),
    BaseVerticalView {

    lateinit var adapterRecView: DataRecyclerViewAdapter

    private var isLoading = false
    override fun finishLoading() {
        isLoading = false
    }

    override fun startLoading() {
        isLoading = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterRecView = DataRecyclerViewAdapter(ViewHolderType.VERTICAL, this, context!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vertical, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).disableDrawerLayout()
        vertical_item_recycler_view.apply {
            val gridLayoutManager = GridLayoutManager(context, 2)
            layoutManager = gridLayoutManager
            adapter = adapterRecView
            addItemDecoration(
                VerticalItemDecorator(context, gridLayoutManager.orientation).apply {
                    setDrawable(
                        ContextCompat.getDrawable(this.context, android.R.color.transparent)!!
                    )
                }
            )
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if (isLoading) {
                        return
                    }
                    val visibleItemCount = gridLayoutManager.childCount
                    val totalItemCount = gridLayoutManager.itemCount
                    val firstVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition + THRESHOLD >= totalItemCount) {
                        presenter.downloadNextPage()
                    }
                }
            })
        }
        presenter.initView()
    }

    override fun updateAdapter(shortInfoList: List<ShortInfo>) {
        adapterRecView.updateAdapter(shortInfoList)
    }
}