package com.pppp.movies.apis

import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface MoviesApi {
    @GET("/3/search/movie")
    fun search(@QueryMap params: Map<String, String>): Observable<MoviesSearchResult>

    @GET("/3/search/movie")
    fun searchSync(@QueryMap params: Map<String, String>): Call<MoviesSearchResult>

    @GET("/3/movie/{id}")
    fun getDetail(@Path("id") id: Int, @QueryMap params: Map<String, String>): Observable<MovieDetail>
}