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
            val previous = calculatePrevious(searchResult)
            callback.onResult(it, previous, calculateNextKey(searchResult))
        }
    }

    private fun calculatePrevious(searchResult: MoviesSearchResult): String? {
        if (searchResult.page <= 1) {
            return null
        }
        return Math.max(1, searchResult.page - 1).toString()
    }

    private fun calculateNextKey(searchResult: MoviesSearchResult): String? {
        if (searchResult.totalPages <= searchResult.page) {
            return null
        }
        return Math.min(searchResult.totalPages, searchResult.page + 1).toString()
    }

    private fun performQuery(params: LoadParams<String>) = model.searchSync(query, params.key)
}