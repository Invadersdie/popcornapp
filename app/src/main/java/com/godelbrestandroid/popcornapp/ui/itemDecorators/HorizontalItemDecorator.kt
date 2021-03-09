package com.godelbrestandroid.popcornapp.ui.itemDecorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class HorizontalItemDecorator(val context: Context, layoutManagerOrientation: Int) :
    DividerItemDecoration(context, layoutManagerOrientation) {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        fun getSizeInDp(sizeInDp: Int): Int {
            return (sizeInDp * context.resources.displayMetrics.density + 0.5f).toInt()
        }
        with(outRect) {
            left = getSizeInDp(8)
            if (parent.getChildAdapterPosition(view) == 0) {
                left = getSizeInDp(16)
            }
            if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1)) {
                right = getSizeInDp(16)
            }
            top = 0
        }
    }
}