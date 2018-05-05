package com.pppp.movies.detail.view

import com.pppp.movies.apis.detail.MovieDetail

interface DetailsView {
    fun onError(throwable: Throwable)
    fun onDetailsAvailable(detail: MovieDetail)

}