package com.pppp.movies.detail.model

import com.pppp.movies.API_KEY_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.apis.detail.MovieDetail
import io.reactivex.Observable

class DetailModelRetrofit(
        val api: MoviesApi,
        val apiKey: String) : DetailModel {

    override fun getMovieDetail(movie: Int): Observable<MovieDetail> {
        val map = HashMap<String, String>()
        map.put(API_KEY_KEY, apiKey)
        return api.getDetail(movie, map).map { it }
    }
}