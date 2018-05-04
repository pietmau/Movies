package com.pppp.movies.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("page")
    @Expose
    private val page: Int = 0,
    @SerializedName("total_results")
    @Expose
    private val totalResults: Int = 0,
    @SerializedName("total_pages")
    @Expose
    private val totalPages: Int = 0,
    @SerializedName("results")
    @Expose
    private val results: List<Result>? = null
)