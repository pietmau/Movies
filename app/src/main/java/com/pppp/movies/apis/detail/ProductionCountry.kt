package com.pppp.movies.apis.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCountry(
        @SerializedName("iso_3166_1")
        @Expose
        private val iso31661: String? = null,
        @SerializedName("name")
        @Expose
        private val name: String? = null
)
