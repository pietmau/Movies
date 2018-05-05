package com.pppp.movies

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Parcelable
import android.support.annotation.DrawableRes
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.detail.view.DetailActivity

fun <T : Parcelable> Activity.getExtra(key: String) = intent.getParcelableExtra<T>(key)

fun DetailActivity.getMovie(key: String) = getExtra<Movie>(key)

@SuppressLint("NewApi")
fun Context.findDrawable(@DrawableRes resId: Int): Drawable? {
    return if (isLollipopOrAbove) {
        resources.getDrawable(resId, theme)
    } else {
        resources.getDrawable(resId)
    }
}

val isLollipopOrAbove: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP