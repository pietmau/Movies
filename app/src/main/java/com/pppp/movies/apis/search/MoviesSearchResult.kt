package com.pppp.movies.apis.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MoviesSearchResult(
        @SerializedName("page")
        @Expose
        val page: Int = 0,
        @SerializedName("total_results")
        @Expose
        private val totalResults: Int = 0,
        @SerializedName("total_pages")
        @Expose
        val totalPages: Int = 0,
        @SerializedName("results")
        @Expose
        val movies: List<Movie>? = null
)