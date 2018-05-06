package com.pppp.movies.favourite.view.custom

import android.content.Context
import android.content.res.Configuration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.pppp.movies.R
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.application.MoviesApp
import com.pppp.movies.imageloader.ImageLoader
import com.pppp.movies.search.view.custom.MoviHolder
import com.pppp.movies.search.view.custom.MovieSearchResultAdapter

class FavoriteMoviesRecycler @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    private val favoriteMoviesAdapter
        get() = (adapter as FavoriteMoviesAdapter)

    private val columnsNumber: Int
        get() = if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 3

    init {
        layoutManager = GridLayoutManager(context, columnsNumber)
        val loader = (context.applicationContext as MoviesApp).applicationComponent.imageLoader()
        adapter = FavoriteMoviesAdapter(loader)
    }

    fun setCallback(callback: MovieSearchResultAdapter.Callback) {
        favoriteMoviesAdapter.callback = callback
    }

    fun setMovies(movies: List<Movie>) {
        favoriteMoviesAdapter.setMovies(movies)
    }


    class FavoriteMoviesAdapter(private val loader: ImageLoader) : RecyclerView.Adapter<MoviHolder>() {
        private val data = mutableListOf<Movie>()

        override fun getItemCount(): Int = data.size

        lateinit var callback: MovieSearchResultAdapter.Callback

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviHolder {
            return MoviHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false), loader)
        }

        override fun onBindViewHolder(holder: MoviHolder, position: Int) {
            data[position]?.let { holder.bind(it, callback) }
        }

        fun setMovies(movies: List<Movie>) {
            data.clear()
            data.addAll(movies)
            notifyDataSetChanged()
        }

    }
}