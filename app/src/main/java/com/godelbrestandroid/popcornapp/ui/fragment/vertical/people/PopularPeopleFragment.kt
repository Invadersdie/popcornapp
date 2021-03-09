package com.godelbrestandroid.popcornapp.ui.fragment.vertical.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.adapters.DataRecyclerViewAdapter
import com.godelbrestandroid.popcornapp.adapters.ViewHolderType
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.presentation.presenter.vertical.people.PopularPeoplePresenter
import com.godelbrestandroid.popcornapp.presentation.view.vertical.people.PopularPeopleView
import com.godelbrestandroid.popcornapp.ui.activity.MainActivity
import com.godelbrestandroid.popcornapp.ui.fragment.BaseFragment
import com.godelbrestandroid.popcornapp.ui.fragment.vertical.THRESHOLD
import com.godelbrestandroid.popcornapp.ui.itemDecorators.VerticalItemDecorator
import kotlinx.android.synthetic.main.fragment_vertical.*

class PopularPeopleFragment : BaseFragment<PopularPeoplePresenter>(),
    PopularPeopleView {

    companion object {
        fun newInstance(): PopularPeopleFragment {
            val fragment = PopularPeopleFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }

    private var isLoading = false
    override fun finishLoading() {
        isLoading = false
    }

    override fun startLoading() {
        isLoading = true
    }

    private lateinit var adapterRecView: DataRecyclerViewAdapter

    @ProvidePresenter
    fun providePresenter(): PopularPeoplePresenter = lazyPresenter.get()

    @InjectPresenter
    override lateinit var presenter: PopularPeoplePresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapterRecView = DataRecyclerViewAdapter(ViewHolderType.VERTICAL, this, context!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).enableDrawerLayout()
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vertical, container, false)
    }

    override fun updateAdapter(shortInfoList: List<ShortInfo>) {
        adapterRecView.updateAdapter(shortInfoList)
    }
}