package com.pppp.movies.search.model

import android.arch.paging.DataSource
import com.pppp.movies.apis.search.Movie

class DataSourceFactory(val model: SearchModel, val query: String) : DataSource.Factory<String, Movie>() {

    override fun create(): DataSource<String, Movie> = MoviesDataSource(model, query)

}


