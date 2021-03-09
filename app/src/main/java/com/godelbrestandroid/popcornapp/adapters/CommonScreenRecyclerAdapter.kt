package com.godelbrestandroid.popcornapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.models.CommonScreenItem
import com.godelbrestandroid.popcornapp.ui.fragment.horizontal.BaseHorizontalFragment
import javax.inject.Inject


class CommonScreenRecyclerAdapter @Inject constructor(private val fragment: BaseHorizontalFragment<*>) :
    RecyclerView.Adapter<CommonScreenViewHolder>() {

    private var commonScreenItemList: List<CommonScreenItem> = emptyList()

    fun updateAdapterData(commonScreenItemList: List<CommonScreenItem>) {
        this.commonScreenItemList = commonScreenItemList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: CommonScreenViewHolder, position: Int) {
        val dataModel = commonScreenItemList[position]
        holder.setTitle(dataModel.title)
        holder.setOnMoreClickListener {
            fragment.onMoreClick(
                dataModel.title,
                dataModel.shortInfoList[0].dataType
            )
        }
        holder.updateDataAdapter(dataModel.shortInfoList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonScreenViewHolder {
        return CommonScreenViewHolder(
            fragment,
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_horizontal_rec_view_with_title, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return commonScreenItemList.size
    }
}

