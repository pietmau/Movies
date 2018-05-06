package com.pppp.movies.search.viewmodel

import android.arch.paging.PagedList
import com.pppp.movies.apis.search.Movie

interface SearchView {
    fun startDetailScreen(movie: Movie)

    fun onError(throwable: Throwable)

    fun onMovieAvailable(movies: PagedList<Movie>)

    fun showProgress(show: Boolean)
}