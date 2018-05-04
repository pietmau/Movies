package com.pppp.movies.main.view.custom

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pppp.movies.R
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.imageloader.ImageLoader

class MovieSearchResultAdapter(private val loader: ImageLoader) : RecyclerView.Adapter<MovieSearchResultHolder>() {
    lateinit var callback: Callback
    var data: List<Movie> = mutableListOf()
    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchResultHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return MovieSearchResultHolder(view, loader)
    }

    override fun onBindViewHolder(holder: MovieSearchResultHolder, position: Int) {
        holder.bind(data[position], callback)
    }

    interface Callback {
        fun onItemClicked(movie: Movie)
    }
}