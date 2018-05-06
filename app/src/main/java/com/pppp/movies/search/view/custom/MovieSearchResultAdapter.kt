package com.pppp.movies.search.view.custom

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pppp.movies.R
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.imageloader.ImageLoader

class MovieSearchResultAdapter(private val loader: ImageLoader) : PagedListAdapter<Movie, MovieSearchResultHolder>(CALLBACK) {
    lateinit var callback: Callback
    var data: List<Movie> = mutableListOf()
    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return MovieSearchResultHolder(view, loader)
    }

    override fun onBindViewHolder(holder: MovieSearchResultHolder, position: Int) {
        val movie = data[position]
        if (movie != null) {
            holder.bind(movie, callback)
        }else{
            //holder.clear()
        }
    }

    interface Callback {
        fun onItemClicked(movie: Movie)
    }

    object CALLBACK : DiffUtil.ItemCallback<Movie>() {
        override fun areContentsTheSame(oldItem: Movie?, newItem: Movie?): Boolean {
            return false
        }

        override fun areItemsTheSame(oldItem: Movie?, newItem: Movie?): Boolean {
            return false
        }
    }
}