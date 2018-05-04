package com.pppp.movies.apis.search

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("vote_count")
    @Expose
    private val voteCount: Int = 0,
    @SerializedName("id")
    @Expose
    private val id: Int = 0,
    @SerializedName("video")
    @Expose
    private val video: Boolean = false,
    @SerializedName("vote_average")
    @Expose
    private val voteAverage: Double = 0.0,
    @SerializedName("title")
    @Expose
    private val title: String? = null,
    @SerializedName("popularity")
    @Expose
    private val popularity: Double = 0.toDouble(),
    @SerializedName("poster_path")
    @Expose
    val posterPath: String? = null,
    @SerializedName("original_language")
    @Expose
    private val originalLanguage: String? = null,
    @SerializedName("original_title")
    @Expose
    private val originalTitle: String? = null,
    @SerializedName("genre_ids")
    @Expose
    private val genreIds: List<Int>? = null,
    @SerializedName("backdrop_path")
    @Expose
    private val backdropPath: Any? = null,
    @SerializedName("adult")
    @Expose
    private val adult: Boolean = false,
    @SerializedName("overview")
    @Expose
    private val overview: String? = null,
    @SerializedName("release_date")
    @Expose
    private val releaseDate: String? = null
)
