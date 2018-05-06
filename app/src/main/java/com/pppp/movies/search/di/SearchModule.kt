package com.pppp.movies.search.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.search.model.SearchModel
import com.pppp.movies.search.model.SearchModelRetrofit
import com.pppp.movies.search.viewmodel.SearchPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@SearchScope
@Module
class SearchModule(val activity: FragmentActivity) {

    companion object {
        private const val API_KEY = "4cb1eeab94f45affe2536f2c684a5c9e"
    }

    @SearchScope
    @Provides
    fun providesPresenter(model: SearchModel, factory: MainPresenterFactory)
            = ViewModelProviders.of(activity, factory).get(SearchPresenter::class.java)

    @Provides
    fun providesModel(api: MoviesApi): SearchModel = SearchModelRetrofit(api, API_KEY)

    @Provides
    fun providesFactory(model: SearchModel): MainPresenterFactory = MainPresenterFactory(model)

    class MainPresenterFactory(val model: SearchModel) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return SearchPresenter(model, AndroidSchedulers.mainThread(), Schedulers.io()) as T
        }
    }
}