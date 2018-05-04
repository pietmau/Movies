package com.pppp.movies.main.view.custom

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.imageloader.ImageLoader
import kotlinx.android.synthetic.main.search_item.view.*

class MovieSearchResultHolder(view: View, val laoder: ImageLoader) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie) {
        laoder.loadPoster(movie.posterPath, itemView.image,100 )
    }

}