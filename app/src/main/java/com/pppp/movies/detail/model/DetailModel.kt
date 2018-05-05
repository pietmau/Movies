package com.pppp.movies.detail.model

import com.pppp.movies.apis.detail.MovieDetail
import io.reactivex.Observable

interface DetailModel {
    fun getMovieDetail(movie: Int): Observable<MovieDetail>
}
