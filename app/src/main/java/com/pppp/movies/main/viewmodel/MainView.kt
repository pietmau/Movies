package com.pppp.movies.main.viewmodel

import com.pppp.movies.apis.search.Movie

interface MainView {
    fun startDetailScreen(movie: Movie)

    fun onError(throwable: Throwable)

    fun onMovieAvailable(moviesSearchResult: List<Movie>)

    fun showProgress(show: Boolean)
}