package com.pppp.movies.apis

import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface MoviesApi {
    @GET("/3/search/movie")
    fun search(@QueryMap params: Map<String, String>): Observable<MoviesSearchResult>
}