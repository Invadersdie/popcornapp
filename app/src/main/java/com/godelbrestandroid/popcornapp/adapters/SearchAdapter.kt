package com.godelbrestandroid.popcornapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.ui.fragment.BaseFragment
import com.godelbrestandroid.popcornapp.utils.StringUtils

class SearchAdapter(val fragment: BaseFragment<*>) : RecyclerView.Adapter<SearchViewHolder>() {

    private var shortInfoList: List<ShortInfo> = emptyList()

    fun updateAdapter(shortInfoList: List<ShortInfo>) {
        this.shortInfoList = shortInfoList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_item_search, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return shortInfoList.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val dataModel = shortInfoList[position]
        holder.updateView(dataModel)
        holder.view.setOnClickListener { fragment.onItemClick(dataModel.dataType, dataModel.id) }
    }
}

class SearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    private val poster: ImageView = view.findViewById(R.id.item_photo_search)
    private val title: TextView = view.findViewById(R.id.item_name_search)
    private val addInfo: TextView = view.findViewById(R.id.item_additional_search)
    private val itemType: TextView = view.findViewById(R.id.item_type_search)


    fun updateView(item: ShortInfo) {
        title.text = item.title
        addInfo.text = item.addInfo
        itemType.text = when (item.dataType) {
            MediaType.movie -> "movie"
            MediaType.tv -> "tv show"
            MediaType.person -> "people"
        }
        Glide.with(view)
            .load(StringUtils.base_url_image + StringUtils.image_size + item.posterUrl)
            .apply(
                RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888).override(
                    Target.SIZE_ORIGINAL
                )
            )
            .into(poster)
    }
}