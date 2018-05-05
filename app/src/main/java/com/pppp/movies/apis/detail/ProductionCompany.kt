package com.pppp.movies.apis.detail

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ProductionCompany(
        @SerializedName("id")
        @Expose
        private val id: Int = 0,
        @SerializedName("logo_path")
        @Expose
        private val logoPath: Any? = null,
        @SerializedName("name")
        @Expose
        private val name: String? = null,
        @SerializedName("origin_country")
        @Expose
        private val originCountry: String? = null
)
