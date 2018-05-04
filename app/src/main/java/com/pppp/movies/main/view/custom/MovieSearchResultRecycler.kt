package com.pppp.movies.main.view.custom

import android.content.Context
import android.content.res.Configuration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.pppp.movies.apis.search.Movie

class MovieSearchResultRecycler @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : RecyclerView(context, attrs, defStyle) {

    var data: List<Movie>
        set(value) {
            (adapter as MovieSearchResultAdapter).data = value
            adapter.notifyDataSetChanged()//TODO remove!!!!!!!!!!
        }
        get() = emptyList()

    private val columnsNumber: Int
        get() = if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) 5 else 3

    init {
        layoutManager = GridLayoutManager(context, columnsNumber)
        adapter = MovieSearchResultAdapter()
    }
}