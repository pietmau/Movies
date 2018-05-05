package com.pppp.movies

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.support.annotation.DrawableRes
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.detail.view.DetailActivity

fun <T : Parcelable> Activity.getExtra(key: String) = intent.getParcelableExtra<T>(key)

fun DetailActivity.getMovie(key: String) = getExtra<Movie>(key)

@SuppressLint("NewApi")
fun Context.findDrawable(@DrawableRes resId: Int) =
        if (isLollipopOrAbove) {
            resources.getDrawable(resId, theme)
        } else {
            resources.getDrawable(resId)
        }

val isLollipopOrAbove: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun View.show(show: Boolean) {
    if (show) {
        visibility = VISIBLE
    } else {
        visibility = INVISIBLE
    }
}

val View.isVisible: Boolean
    get() = visibility == VISIBLE
