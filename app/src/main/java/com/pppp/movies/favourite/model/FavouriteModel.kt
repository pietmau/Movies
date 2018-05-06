package com.pppp.movies.favourite.model

import com.pppp.movies.apis.search.Movie
import io.reactivex.Observable

interface FavouriteModel {

    fun subscribe(): Observable<List<Movie>>

}