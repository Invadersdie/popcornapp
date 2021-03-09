package com.godelbrestandroid.popcornapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.internet.models.movies.Review
import com.godelbrestandroid.popcornapp.ui.view.TAG

class ReviewsAdapter :
    RecyclerView.Adapter<ReviewViewHolder>() {

    private var dataList: List<Review> = emptyList()

    fun updateAdapter(dataList: List<Review>) {
        this.dataList = dataList
        notifyDataSetChanged()
    }

    private fun expandCollapseView(holder: ReviewViewHolder) {
        holder.expandCollapseView()
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.setData(dataList[position])
        holder.view.setOnClickListener { expandCollapseView(holder) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_item_review, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return when {
            dataList.isEmpty() -> 0
            dataList.size < 3 -> dataList.size
            else -> 3
        }
    }
}

class ReviewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val reviewerName = view.findViewById<TextView>(R.id.item_name_review)
    private val reviewText = view.findViewById<TextView>(R.id.item_full_review)
    private var heightReviewTextCollapsed: Int = 0
    private var heightReviewTextExpanded: Int = 0
    private var deltaHeight: Int = 0
    private var duration: Long = 0
    private var isExpanded = false

    private fun setHeights() {
        heightReviewTextCollapsed = reviewText.height
        reviewText.layoutParams =
            (reviewText.layoutParams.apply { height = heightReviewTextCollapsed })
        val matchParentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(reviewText.width, View.MeasureSpec.EXACTLY)
        val wrapContentMeasureSpec =
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        reviewText.maxLines = reviewText.lineCount
        reviewText.measure(matchParentMeasureSpec, wrapContentMeasureSpec)
        heightReviewTextExpanded = reviewText.measuredHeight

        deltaHeight = heightReviewTextExpanded - heightReviewTextCollapsed
    }

    fun expandCollapseView() {
        isExpanded = !isExpanded
        if (heightReviewTextCollapsed == 0 && heightReviewTextExpanded == 0) {
            setHeights()
            duration = (deltaHeight / 4).toLong()
        }
        val animation =
            if (isExpanded) {
                object : Animation() {
                    val layoutParams = reviewText.layoutParams
                    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                        Log.d(TAG, interpolatedTime.toString())
                        reviewText.layoutParams = layoutParams.apply {
                            height =
                                heightReviewTextCollapsed + (deltaHeight * interpolatedTime).toInt()
                        }
                    }

                    override fun willChangeBounds(): Boolean {
                        return true
                    }
                }
            } else {
                object : Animation() {
                    val layoutParams = reviewText.layoutParams
                    override fun applyTransformation(interpolatedTime: Float, t: Transformation) {
                        reviewText.layoutParams = layoutParams.apply {
                            height =
                                heightReviewTextExpanded - (deltaHeight * interpolatedTime).toInt()
                        }
                    }

                    override fun willChangeBounds(): Boolean {
                        return true
                    }
                }
            }

        animation.duration = duration
        reviewText.startAnimation(animation)
    }

    fun setData(review: Review) {
        reviewerName.text = review.author
        reviewText.text = review.content
    }
}