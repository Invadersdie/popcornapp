package com.godelbrestandroid.popcornapp.ui.fragment.horizontal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.adapters.CommonScreenRecyclerAdapter
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.data.models.CommonScreenItem
import com.godelbrestandroid.popcornapp.presentation.presenter.horizontal.BaseHorizontalPresenter
import com.godelbrestandroid.popcornapp.ui.activity.MainActivity
import com.godelbrestandroid.popcornapp.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_horizontal.*

abstract class BaseHorizontalFragment<P : BaseHorizontalPresenter<*>> : BaseFragment<P>() {

    abstract override var presenter: P

    lateinit var adapterRecView: CommonScreenRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterRecView = CommonScreenRecyclerAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_horizontal, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).enableDrawerLayout()
        horizontal_rec_view.apply {
            layoutManager = LinearLayoutManager(
                this@BaseHorizontalFragment.context,
                RecyclerView.VERTICAL,
                false
            )
            adapter = adapterRecView
        }
        presenter.initView()
    }

    fun updateAdapter(commonScreenItemList: List<CommonScreenItem>) {
        adapterRecView.updateAdapterData(commonScreenItemList = commonScreenItemList)
    }

    fun onMoreClick(groupName: String, type: MediaType) {
        presenter.onMoreClick(groupName, type)
    }

}