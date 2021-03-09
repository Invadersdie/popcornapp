package com.godelbrestandroid.popcornapp.ui.view

import android.content.Context
import android.graphics.Bitmap
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class BlurView : AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )

    private var bitmapToBlur: Bitmap? = null
    private var currentRadius: Float = 0f

    fun setBitmap(bm: Bitmap) {
        bitmapToBlur = bm
        super.setImageBitmap(bm)
    }

    fun setBlurAmount(radius: Float) {
        when (radius) {
            0f -> this.setImageBitmap(bitmapToBlur)
            currentRadius -> return
            else -> this.setImageBitmap(bitmapToBlur?.let { blurRenderScript(it, radius) })
        }
    }

    private fun blurRenderScript(smallBitmap: Bitmap, radius: Float): Bitmap {

        val width = smallBitmap.width
        val height = smallBitmap.height

        val inputBitmap = Bitmap.createScaledBitmap(smallBitmap, width, height, false)
        val outputBitmap = Bitmap.createBitmap(inputBitmap)

        val renderScript = RenderScript.create(context)
        val theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript))
        val tmpIn = Allocation.createFromBitmap(renderScript, inputBitmap)
        val tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap)
        theIntrinsic.setRadius(radius)
        theIntrinsic.setInput(tmpIn)
        theIntrinsic.forEach(tmpOut)
        tmpOut.copyTo(outputBitmap)

        return outputBitmap
    }
}