package com.godelbrestandroid.popcornapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.ListPreloader
import com.bumptech.glide.RequestBuilder
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.ui.Clickable
import com.godelbrestandroid.popcornapp.utils.StringUtils
import java.util.*

class DataRecyclerViewAdapter(
    private val orientation: ViewHolderType,
    private val itemClickHandler: Clickable,
    val context: Context
) :
    RecyclerView.Adapter<ViewHolder>(),
    ListPreloader.PreloadModelProvider<String> {

    private var shortInfoList: List<ShortInfo> = emptyList()

    fun updateAdapter(shortInfoList: List<ShortInfo>) {
        if (shortInfoList.isEmpty()) return
        if (!this.shortInfoList.contains(shortInfoList[0])) {
            val oldCount = this.shortInfoList.size
            val insertedCount = shortInfoList.size
            this.shortInfoList = this.shortInfoList.plus(shortInfoList)
            notifyItemRangeInserted(oldCount, insertedCount)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = shortInfoList[0].dataType
        val dataModel = shortInfoList[position]
        holder.updateView(dataModel)
        holder.view.setOnClickListener { itemClickHandler.onItemClick(type, dataModel.id) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (orientation) {
            ViewHolderType.VERTICAL -> VerticalViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item_more, parent, false)
            )
            ViewHolderType.HORIZONTAL_WITH_RATE -> HorizontalWithRateViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item_with_rate, parent, false)
            )
            ViewHolderType.HORIZONTAL -> HorizontalViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_item, parent, false)
            )
        }
    }

    override fun getItemCount(): Int {
        return shortInfoList.size
    }

    override fun getPreloadItems(position: Int): MutableList<String> {
        return Collections.singletonList(shortInfoList[position].posterUrl)
    }

    override fun getPreloadRequestBuilder(imageUrl: String): RequestBuilder<*>? {
        return Glide.with(context)
            .load(StringUtils.base_url_image + StringUtils.image_size + imageUrl)
    }
}

enum class ViewHolderType {
    VERTICAL,
    HORIZONTAL,
    HORIZONTAL_WITH_RATE
}
