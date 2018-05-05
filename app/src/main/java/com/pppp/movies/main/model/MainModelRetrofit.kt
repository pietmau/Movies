package com.pppp.movies.main.model

import com.pppp.movies.API_KEY_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable

class MainModelRetrofit(
        val api: MoviesApi,
        val apiKey: String) : MainModel {
    companion object {
        private const val QUERY_KEY = "query"
    }

    override fun search(query: String): Observable<MoviesSearchResult> {
        val map = HashMap<String, String>()
        map.put(API_KEY_KEY, apiKey)
        map.put(QUERY_KEY, query)
        return api.search(map)
    }
}