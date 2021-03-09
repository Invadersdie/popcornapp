package com.godelbrestandroid.popcornapp.ui.itemDecorators

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView

class VerticalItemDecorator(val context: Context, layoutManagerOrientation: Int) :
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

            top = getSizeInDp(16)
            left = getSizeInDp(8)
            right = getSizeInDp(8)
            bottom = getSizeInDp(8)


            if (parent.getChildAdapterPosition(view) % 2 != 0) {
                right = getSizeInDp(16)
            } else {
                left = getSizeInDp(16)
            }
            if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1) ||
                parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(2)
            ) {
                bottom = getSizeInDp(16)
            }

        }
    }
}