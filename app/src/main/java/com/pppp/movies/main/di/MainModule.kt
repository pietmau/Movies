package com.pppp.movies.main.di

import com.pppp.movies.apis.MovieService
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.main.model.MainModel
import com.pppp.movies.main.model.MainModelRetrofit
import com.pppp.movies.main.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@MainActivityScope
@Module
class MainModule {

    companion object {
        private const val API_KEY = "4cb1eeab94f45affe2536f2c684a5c9e"
    }

    @Provides
    fun providesPresenter(model: MainModel) = MainViewModel(model, AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    fun providesModeel(api: MoviesApi): MainModel = MainModelRetrofit(api, API_KEY)

    @MainActivityScope
    @Provides
    fun providesApi(): MoviesApi = MovieService
}