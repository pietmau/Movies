package com.pppp.movies.detail.model

import com.pppp.movies.apis.detail.MovieDetail
import io.reactivex.Observable

interface DetailRepository {

    fun getMovieDetail(movie: Int): Observable<MovieDetail>

    fun changeFavourite(movieDetail: MovieDetail)

    fun getMovieDetailFromDb(id: Int): Observable<MovieDetail>
}

