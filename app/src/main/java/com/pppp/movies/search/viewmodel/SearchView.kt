package com.pppp.movies.search.viewmodel

import com.pppp.movies.apis.search.Movie

interface SearchView {
    fun startDetailScreen(movie: Movie)

    fun onError(throwable: Throwable)

    fun onMovieAvailable(moviesSearchResult: List<Movie>)

    fun showProgress(show: Boolean)
}