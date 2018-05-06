package com.pppp.movies.search.model

import android.arch.paging.DataSource
import com.pppp.movies.apis.search.Movie


fun ff() {
//    val pagedItems = RxPagedListBuilder(DataSourceFactory(), 20)
//            .setFetchScheduler(Schedulers.io())
//            .buildFlowable(BackpressureStrategy.LATEST)

}

class DataSourceFactory(val model: SearchModel, val query: String) : DataSource.Factory<String, Movie>() {

    override fun create(): DataSource<String, Movie> = MoviesDataSource(model, query)

}


