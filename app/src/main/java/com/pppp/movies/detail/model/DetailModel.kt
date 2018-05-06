package com.pppp.movies.detail.model

import com.pppp.movies.apis.detail.MovieDetail
import io.reactivex.Observable

interface DetailModel {

    fun getMovieDetailFromNewtwork(movie: Int): Observable<MovieDetail>

    fun onFavouritePressed(movieDetail: MovieDetail)

    fun getMovieDetailFromDb(id: Int): Observable<MovieDetail>
}

