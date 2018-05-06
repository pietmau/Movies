package com.pppp.movies.search.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.FragmentActivity
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.search.model.MainModel
import com.pppp.movies.search.model.MainModelRetrofit
import com.pppp.movies.search.viewmodel.MainPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@MainActivityScope
@Module
class MainModule(val activity: FragmentActivity) {

    companion object {
        private const val API_KEY = "4cb1eeab94f45affe2536f2c684a5c9e"
    }

    @MainActivityScope
    @Provides
    fun providesPresenter(model: MainModel, factory: MainPresenterFactory)
            = ViewModelProviders.of(activity, factory).get(MainPresenter::class.java)

    @Provides
    fun providesModel(api: MoviesApi): MainModel = MainModelRetrofit(api, API_KEY)

    @Provides
    fun providesFactory(model: MainModel): MainPresenterFactory = MainPresenterFactory(model)

    class MainPresenterFactory(val model: MainModel) : ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainPresenter(model, AndroidSchedulers.mainThread(), Schedulers.io()) as T
        }
    }
}