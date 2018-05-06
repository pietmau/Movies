package com.pppp.movies.favourite.view

import com.pppp.movies.apis.search.Movie

interface FavouriteView {

    fun onError(throwable: Throwable)

    fun onMoviesRetrieved(movies: List<Movie>)

    fun showDetaiScreen(movie: Movie)

}