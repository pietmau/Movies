package com.pppp.movies.search.model

import android.arch.paging.PageKeyedDataSource
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.apis.search.MoviesSearchResult

class MoviesDataSource(
        private val model: SearchModel,
        private val query: String
) : PageKeyedDataSource<String, Movie>() {

    override fun loadAfter(params: LoadParams<String>, callback: LoadCallback<String, Movie>) {
        val searchResult = performQuery(params)
        searchResult?.movies?.let {
            callback.onResult(it, calculateNextKey(searchResult))
        }
    }

    override fun loadBefore(params: LoadParams<String>, callback: LoadCallback<String, Movie>) {
        val searchResult = performQuery(params)
        searchResult?.movies?.let {
            callback.onResult(it, calculateNextKey(searchResult))
        }
    }

    override fun loadInitial(params: LoadInitialParams<String>, callback: LoadInitialCallback<String, Movie>) {
        val searchResult = model.searchSync(query)
        searchResult?.movies?.let {
            val previous = Math.max(1, searchResult.page - 1).toString()
            callback.onResult(it, previous, calculateNextKey(searchResult))
        }
    }

    private fun calculateNextKey(searchResult: MoviesSearchResult) = Math.min(searchResult.totalPages, searchResult.page + 1).toString()

    private fun performQuery(params: LoadParams<String>) = model.searchSync(query, params.key)
}