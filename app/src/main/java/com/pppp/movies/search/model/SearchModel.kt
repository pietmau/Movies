package com.pppp.movies.search.model

import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable

interface SearchModel {

    fun searchAsync(query: String): Observable<MoviesSearchResult>

    fun searchSync(query: String): MoviesSearchResult?

    fun searchSync(query: String, key: String): MoviesSearchResult?
}