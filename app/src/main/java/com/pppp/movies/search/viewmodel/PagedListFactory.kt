package com.pppp.movies.search.viewmodel

import android.arch.paging.RxPagedListBuilder
import com.pppp.movies.search.model.DataSourceFactory
import com.pppp.movies.search.model.SearchModel
import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers

class PagedListFactory(private val model: SearchModel) {

    fun getPagesList(query: String = "", pageSize: Int = 20) =
            RxPagedListBuilder(DataSourceFactory(model, query), pageSize)
                    .setFetchScheduler(Schedulers.io())
                    .buildFlowable(BackpressureStrategy.LATEST)
}