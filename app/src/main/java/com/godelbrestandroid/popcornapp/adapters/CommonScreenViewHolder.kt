package com.godelbrestandroid.popcornapp.adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.ui.applyRecViewHorizontalSettings
import com.godelbrestandroid.popcornapp.ui.fragment.BaseFragment

class CommonScreenViewHolder(fragment: BaseFragment<*>, val view: View) :
    RecyclerView.ViewHolder(view) {

    private val title: TextView = view.findViewById(R.id.horizontal_item_title)
    private val more: TextView = view.findViewById(R.id.horizontal_item_more)
    private val recyclerView: RecyclerView = view.findViewById(R.id.horizontal_item_recycler_view)
    private val adapterRecView: DataRecyclerViewAdapter =
        DataRecyclerViewAdapter(ViewHolderType.HORIZONTAL, fragment, view.context)

    init {
        recyclerView.applyRecViewHorizontalSettings(adapterRecView)
    }

    fun setTitle(title: String) {
        this.title.text = title
    }

    fun setOnMoreClickListener(listener: () -> Unit) {
        this.more.setOnClickListener { listener.invoke() }
    }

    fun updateDataAdapter(shortInfoList: List<ShortInfo>) {
        adapterRecView.updateAdapter(shortInfoList)
    }

}