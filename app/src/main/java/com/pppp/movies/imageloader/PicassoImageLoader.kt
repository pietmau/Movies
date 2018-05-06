package com.pppp.movies.imageloader

import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoImageLoader(val picasso: Picasso, val baseUrl: String) : ImageLoader {

    override fun load(url: String, view: ImageView) {
        picasso.load(url).into(view)
    }

    override fun cancelRequest(view: ImageView) {
        picasso.cancelRequest(view)
    }

    override fun loadPoster(posterPath: String?, view: ImageView) {
        posterPath?.let { picasso.load(baseUrl + "/t/p/original" + it).into(view) } // Image size not supported!!!
    }
}