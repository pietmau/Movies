package com.pppp.movies.search.model

import com.pppp.movies.API_KEY_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.apis.search.MoviesSearchResult
import io.reactivex.Observable

class SearchModelRetrofit(
        val api: MoviesApi,
        val apiKey: String) : SearchModel {

    companion object {
        private const val QUERY_KEY = "query"
        private const val PAGE_KEY = "page"
    }

    override fun searchAsync(query: String): Observable<MoviesSearchResult> {
        val map = HashMap<String, String>()
        map.put(API_KEY_KEY, apiKey)
        map.put(QUERY_KEY, query)
        return api.search(map)
    }

    override fun searchSync(query: String): MoviesSearchResult? {
        val map = HashMap<String, String>()
        map.put(API_KEY_KEY, apiKey)
        map.put(QUERY_KEY, query)
        return api.searchSync(map).execute().body()
    }

    override fun searchSync(query: String, page: String): MoviesSearchResult? {
        val map = HashMap<String, String>()
        map.put(API_KEY_KEY, apiKey)
        map.put(PAGE_KEY, page)
        map.put(QUERY_KEY, query)
        return api.searchSync(map).execute().body()
    }
}