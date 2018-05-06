package com.pppp.movies.search.view.custom

import android.content.Context
import android.content.res.Configuration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.application.App

class MovieSearchResultRecycler @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    private val movieSearchResultAdapter
        get() = (adapter as MovieSearchResultAdapter)

    private val columnsNumber: Int
        get() = if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 3

    init {
        layoutManager = GridLayoutManager(context, columnsNumber)
        adapter = MovieSearchResultAdapter((context.applicationContext as App).applicationComponent.imageLoader())
    }

    fun setData(value: List<Movie>) {
        movieSearchResultAdapter.data = value
        movieSearchResultAdapter.notifyDataSetChanged()
    }

    fun setCallback(callback: MovieSearchResultAdapter.Callback) {
        movieSearchResultAdapter.callback = callback
    }

}