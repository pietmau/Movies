package com.pppp.movies.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun load(url: String, view: ImageView)
    fun cancelRequest(view: ImageView)
    fun loadPoster(posterPath: String?, view: ImageView)
}