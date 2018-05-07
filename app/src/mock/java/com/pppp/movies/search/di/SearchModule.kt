package com.pppp.movies.search.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.arch.paging.PagedList
import android.support.v4.app.FragmentActivity
import com.jakewharton.rxrelay2.BehaviorRelay
import com.pppp.movies.API_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.search.model.SearchModel
import com.pppp.movies.search.model.SearchModelRetrofit
import com.pppp.movies.search.viewmodel.PagedListFactory
import com.pppp.movies.search.viewmodel.SearchPresenter
import dagger.Module
import dagger.Provides

@SearchScope
@Module
class SearchModule(val activity: FragmentActivity) {

    @SearchScope
    @Provides
    fun providesPresenter(model: SearchModel, factory: MainPresenterFactory)
            = ViewModelProviders.of(activity, factory).get(SearchPresenter::class.java)

    @Provides
    fun providesModel(api: MoviesApi): SearchModel = SearchModelRetrofit(api, API_KEY)

    @Provides
    fun providesFactory(pagedListFactory: PagedListFactory): MainPresenterFactory = MainPresenterFactory(pagedListFactory)

    @Provides
    fun providesPagedListFactory(model: SearchModel) = PagedListFactory(model)

    class MainPresenterFactory(val pagedListFactory: PagedListFactory) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchPresenter(BehaviorRelay.create<PagedList<Movie>>(), pagedListFactory) as T
        }
    }
}