package com.pppp.movies.apis.search

import android.os.Parcel
import android.os.Parcelable
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
    @SerializedName("backdrop_path")
    @Expose
    private val backdropPath: String? = null,
    @SerializedName("adult")
    @Expose
    private val adult: Boolean = false,
    @SerializedName("overview")
    @Expose
    private val overview: String? = null,
    @SerializedName("release_date")
    @Expose
    private val releaseDate: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readByte() != 0.toByte(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readByte() != 0.toByte(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(voteCount)
        parcel.writeInt(id)
        parcel.writeByte(if (video) 1 else 0)
        parcel.writeDouble(voteAverage)
        parcel.writeString(title)
        parcel.writeDouble(popularity)
        parcel.writeString(posterPath)
        parcel.writeString(originalLanguage)
        parcel.writeString(originalTitle)
        parcel.writeString(backdropPath)
        parcel.writeByte(if (adult) 1 else 0)
        parcel.writeString(overview)
        parcel.writeString(releaseDate)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}
