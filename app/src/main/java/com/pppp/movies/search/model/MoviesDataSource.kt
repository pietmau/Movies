package com.pppp.movies.search.model

import android.arch.paging.PageKeyedDataSource
import com.pppp.movies.apis.search.Movie

class MoviesDataSource(
        private val model: SearchModel,
        private val query: String
) : PageKeyedDataSource<String, Movie>() {

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Movie>) {

    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Movie>) {

    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Movie>) {
        val result = model.searchSync(query) ?: return
        result.movies?.let { callback.onResult(it, "0","1" ) }
    }
}