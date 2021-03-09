package com.godelbrestandroid.popcornapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Backdrop
import com.godelbrestandroid.popcornapp.utils.StringUtils

class ImagesAdapter<T> : RecyclerView.Adapter<ImagesViewHolder<T>>() {

    var dataList: List<T> = emptyList()

    fun updateAdapter(dataList: List<T>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder<T> {
        return ImagesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_images, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder<T>, position: Int) {
        holder.updateView(dataList[position])
    }
}

class ImagesViewHolder<T>(val view: View) : RecyclerView.ViewHolder(view) {
    private val imageHolder = view.findViewById<ImageView>(R.id.item_image_images)

    fun updateView(item: T) {
        val path = when (item) {
            is Backdrop -> StringUtils.base_url_image + StringUtils.image_size + item.file_path
            else -> throw Exception("Not implemented")
        }

        Glide.with(view)
            .load(path)
            .into(imageHolder)
    }
}