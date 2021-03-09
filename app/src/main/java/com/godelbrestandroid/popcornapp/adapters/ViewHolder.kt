package com.godelbrestandroid.popcornapp.adapters

import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.models.ShortInfo
import com.godelbrestandroid.popcornapp.utils.StringUtils

sealed class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    abstract val poster: ImageView
    abstract val title: TextView
    abstract val addInfo: View
    fun updateView(item: ShortInfo) {
        title.text = item.title
        setAddInfo(item.addInfo)
        Glide.with(view)
            .load(StringUtils.base_url_image + StringUtils.image_size + item.posterUrl)
            .apply(
                RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888).override(
                    Target.SIZE_ORIGINAL
                )
            )
            .into(poster)
    }

    abstract fun setAddInfo(additionalInfo: String)
}


class HorizontalViewHolder(view: View) : ViewHolder(view) {


    override val poster: ImageView = view.findViewById(R.id.item_photo)
    override val title: TextView = view.findViewById(R.id.item_name)
    override val addInfo: TextView = view.findViewById(R.id.item_additional)
    override fun setAddInfo(additionalInfo: String) {
        addInfo.text = additionalInfo
    }
}

class HorizontalWithRateViewHolder(view: View) : ViewHolder(view) {
    override val poster: ImageView = view.findViewById(R.id.item_photo_more)
    override val title: TextView = view.findViewById(R.id.item_name_more)
    override val addInfo: RatingBar = view.findViewById(R.id.item_additional_more)
    override fun setAddInfo(additionalInfo: String) {
        addInfo.rating = additionalInfo.toFloat() / 2
    }
}

class VerticalViewHolder(view: View) : ViewHolder(view) {
    override val poster: ImageView = view.findViewById(R.id.item_photo_more)
    override val title: TextView = view.findViewById(R.id.item_name_more)
    override val addInfo: TextView = view.findViewById(R.id.item_additional_more)
    override fun setAddInfo(additionalInfo: String) {
        addInfo.text = additionalInfo
    }
}