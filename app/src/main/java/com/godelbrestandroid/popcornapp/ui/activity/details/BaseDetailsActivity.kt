package com.godelbrestandroid.popcornapp.ui.activity.details

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.core.widget.NestedScrollView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.godelbrestandroid.popcornapp.R
import com.godelbrestandroid.popcornapp.data.internet.models.MediaType
import com.godelbrestandroid.popcornapp.presentation.presenter.details.BaseDetailsPresenter
import com.godelbrestandroid.popcornapp.presentation.view.details.BaseDetailsView
import com.godelbrestandroid.popcornapp.ui.Clickable
import com.godelbrestandroid.popcornapp.utils.StringUtils
import dagger.Lazy
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_details_core.*
import kotlinx.android.synthetic.main.details_extend_movie.*
import javax.inject.Inject

const val MAX_TRANSPARENCY = 0xBF
const val INIT_TRANSPARENCY = 0
const val MAX_BLUR = 10f

abstract class BaseDetailsActivity<P : BaseDetailsPresenter<*>, itemType>(@LayoutRes val layoutId: Int) :
    MvpAppCompatActivity(), BaseDetailsView<itemType>, Clickable {

    override fun onItemClick(itemType: MediaType, id: Int) {
        presenter.onItemClick(itemType, id)
    }

    abstract var presenter: P

    @Inject
    lateinit var lazyPresenter: Lazy<P>

    override fun showToast(message: String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun showLoading() {
        detailsLoadingBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        detailsLoadingBar.visibility = View.GONE
    }

    override fun showDetails() {
        expandable_view.expand()
    }

    override fun hideDetails() {
        expandable_view.collapse()
    }

    private var valueId = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_details_core)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.window.apply {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                statusBarColor = Color.TRANSPARENT
            }
            toolbarMarginTop()
        }
        view_stub.apply {
            layoutResource = layoutId
            inflate()
        }
        configActionBar()
        valueId = intent.getIntExtra(StringUtils.ID, 0)
        presenter.initView(valueId)
        details_scroll_view.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            presenter.onScrollChange(scrollY, oldScrollY)
        })
    }

    private fun toolbarMarginTop() {
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        val statusBarHeight = if (resourceId > 0) resources.getDimensionPixelSize(resourceId) else 0
        val menuLayoutParams = details_toolbar.layoutParams as ViewGroup.MarginLayoutParams
        menuLayoutParams.setMargins(0, statusBarHeight, 0, 0)
        details_toolbar.layoutParams = menuLayoutParams
    }

    override fun showMainView() {
        details_scroll_view.visibility = View.VISIBLE
    }

    override fun openDetails(type: MediaType, id: Int) {
        this.startActivity(
            when (type) {
                MediaType.movie -> MovieDetailsActivity.getIntent(baseContext)
                MediaType.tv -> TvShowDetailsActivity.getIntent(baseContext)
                MediaType.person -> PeopleDetailsActivity.getIntent(baseContext)
            }
                .apply {
                    putExtra(StringUtils.ID, id)
                })
    }

    private fun configActionBar() {
        setSupportActionBar(details_toolbar)
        supportActionBar?.setBackgroundDrawable(null)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = null
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun hideToolbar() {
        details_toolbar.animate().translationY(-details_toolbar.bottom.toFloat())
            .setInterpolator(
                AccelerateInterpolator()
            ).start()
    }

    override fun showToolbar() {
        details_toolbar.animate().translationY(0f).setInterpolator(DecelerateInterpolator())
            .start()
    }

    override fun setBlur(newBlur: Float) {
        details_poster.setBlurAmount(newBlur)
    }

    override fun setTransparency(newTransparency: Int) {
        details_poster_foreground.setTransparency(newTransparency)
    }

    override fun setMainImage(path: String?) {
        Glide.with(this)
            .asBitmap()
            .load(pathBuilder(path))
            .apply(
                RequestOptions().fitCenter().format(DecodeFormat.PREFER_ARGB_8888).override(
                    Target.SIZE_ORIGINAL
                )
            )
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    details_poster.setBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })
    }

    private fun pathBuilder(posterPath: String?) =
        StringUtils.base_url_image + StringUtils.image_size_details + posterPath
}