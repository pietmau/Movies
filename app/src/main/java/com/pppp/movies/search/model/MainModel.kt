package com.pppp.movies.search.model

import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable

interface MainModel {
    fun search(query: String): Observable<MoviesSearchResult>
}