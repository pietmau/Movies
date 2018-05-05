package com.pppp.movies.apis.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genre(
        @SerializedName("id")
        @Expose
        private val id: Int = 0,
        @SerializedName("name")
        @Expose
        private val name: String? = null
)
