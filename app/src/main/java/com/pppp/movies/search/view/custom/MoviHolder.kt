package com.pppp.movies.search.view.custom

import android.support.v7.widget.RecyclerView
import android.view.View
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.imageloader.ImageLoader
import kotlinx.android.synthetic.main.search_item.view.*

class MoviHolder(view: View, val loader: ImageLoader) : RecyclerView.ViewHolder(view) {

    fun bind(movie: Movie, callback: MovieSearchResultAdapter.Callback) {
        loader.loadPoster(movie.posterPath, itemView.image)
        itemView.title.text = movie.title
        itemView.setOnClickListener { callback.onMovieSelected(movie) }
    }

}