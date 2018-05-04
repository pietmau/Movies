package com.pppp.movies.apis

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.pppp.movies.BASE_URL
import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.QueryMap


object MovieService : MoviesApi {
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
}