package com.pppp.movies.apis.detail

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class MovieDetail(
        var isFavourite: Boolean = false,
        @SerializedName("adult")
        @Expose
        var isAdult: Boolean = false,
        @SerializedName("backdrop_path")
        @Expose
        var backdropPath: String? = null,
        @SerializedName("belongs_to_collection")

        @Ignore
        @Expose
        var belongsToCollection: Any? = null,
        @SerializedName("budget")
        @Expose
        var budget: Int = 0,
        @SerializedName("genres")

        @Ignore
        @Expose
        var genres: List<Genre>? = null,
        @SerializedName("homepage")
        @Expose
        var homepage: String? = null,
        @PrimaryKey
        @SerializedName("id")
        @Expose
        var id: Int = 0,
        @SerializedName("imdb_id")
        @Expose
        var imdbId: String? = null,
        @SerializedName("original_language")
        @Expose
        var originalLanguage: String? = null,
        @SerializedName("original_title")
        @Expose
        var originalTitle: String? = null,
        @SerializedName("overview")
        @Expose
        var overview: String? = null,
        @SerializedName("popularity")
        @Expose
        var popularity: Double = 0.toDouble(),
        @SerializedName("poster_path")
        @Expose
        var posterPath: String? = null,
        @SerializedName("production_companies")

        @Ignore
        @Expose
        var productionCompanies: List<ProductionCompany>? = null,
        @SerializedName("production_countries")

        @Ignore
        @Expose
        var productionCountries: List<ProductionCountry>? = null,
        @SerializedName("release_date")
        @Expose
        var releaseDate: String? = null,
        @SerializedName("revenue")
        @Expose
        var revenue: Int = 0,
        @SerializedName("runtime")
        @Expose
        var runtime: Int = 0,
        @SerializedName("spoken_languages")

        @Ignore
        @Expose
        var spokenLanguages: List<SpokenLanguage>? = null,
        @SerializedName("status")
        @Expose
        var status: String? = null,
        @SerializedName("tagline")
        @Expose
        var tagline: String? = null,
        @SerializedName("title")
        @Expose
        var title: String? = null,
        @SerializedName("video")
        @Expose
        var isVideo: Boolean = false,
        @SerializedName("vote_average")
        @Expose
        var voteAverage: Double = 0.toDouble(),
        @SerializedName("vote_count")
        @Expose
        var voteCount: Int = 0
) {

}
