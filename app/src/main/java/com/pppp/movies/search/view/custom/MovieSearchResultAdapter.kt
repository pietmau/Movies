package com.pppp.movies.search.view.custom

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pppp.movies.R
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.imageloader.ImageLoader

class MovieSearchResultAdapter(
        private val loader: ImageLoader,
        callback: DiffUtil.ItemCallback<Movie>)
    : PagedListAdapter<Movie, MovieSearchResultHolder>(callback) {

    lateinit var callback: Callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchResultHolder {
        return MovieSearchResultHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false), loader)
    }

    override fun onBindViewHolder(holder: MovieSearchResultHolder, position: Int) {
        val movie = getItem(position)?.let { holder.bind(it, callback) }
    }

    interface Callback {
        fun onItemClicked(movie: Movie)

    }

}