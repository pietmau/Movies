package com.pppp.movies.apis

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pppp.movies.BASE_URL
import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.QueryMap


object MovieService : MoviesApi {
    override fun searchSync(params: Map<String, String>): Call<MoviesSearchResult> = service.searchSync(params)

    private val service: MoviesApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        service = retrofit.create(MoviesApi::class.java)
    }

    override fun search(@QueryMap params: Map<String, String>): Observable<MoviesSearchResult> = service.search(params)

    override fun getDetail(id: Int, params: Map<String, String>): Observable<MovieDetail> = service.getDetail(id, params)
}