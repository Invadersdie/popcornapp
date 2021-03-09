package com.godelbrestandroid.popcornapp.ui.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.godelbrestandroid.popcornapp.ui.activity.details.INIT_TRANSPARENCY
import com.godelbrestandroid.popcornapp.ui.activity.details.MAX_TRANSPARENCY

class GradientView : AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private val startEndColor = Color.argb(MAX_TRANSPARENCY, 0, 0, 0)
    private val midColor = Color.argb(INIT_TRANSPARENCY, 0, 0, 0)
    private val colors =
        intArrayOf(startEndColor, midColor, midColor, midColor, midColor, midColor, startEndColor)
    private var currentTransparency = INIT_TRANSPARENCY

    init {
        this.setImageDrawable(GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors))
    }

    fun setTransparency(newTransparency: Int) {
        if (newTransparency != currentTransparency) {
            currentTransparency = newTransparency
            val newColor = Color.argb(newTransparency, 0, 0, 0)
            colors[1] = newColor
            colors[2] = newColor
            colors[3] = newColor
            colors[4] = newColor
            colors[5] = newColor
            this.setImageDrawable(
                GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
            )
        }
    }
}