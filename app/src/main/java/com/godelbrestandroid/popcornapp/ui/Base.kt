package com.godelbrestandroid.popcornapp.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.godelbrestandroid.popcornapp.ui.itemDecorators.HorizontalItemDecorator

fun RecyclerView.applyRecViewHorizontalSettings(adapter: RecyclerView.Adapter<*>) {
    val linearLayoutManager = LinearLayoutManager(
        this.context, RecyclerView.HORIZONTAL, false
    )
    this.adapter = adapter
    this.layoutManager = linearLayoutManager
    this.addItemDecoration(HorizontalItemDecorator(context, linearLayoutManager.orientation).apply {
        setDrawable(
            ContextCompat.getDrawable(this.context, android.R.color.transparent)!!
        )
    })
}
