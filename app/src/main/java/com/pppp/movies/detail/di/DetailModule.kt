package com.pppp.movies.detail.di


import com.pppp.movies.API_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.database.MovieDetailDao
import com.pppp.movies.detail.model.DetailModel
import com.pppp.movies.detail.model.DetailModelRetrofit
import com.pppp.movies.detail.presenter.DetailPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class DetailModule {

    @Provides
    fun providePresenter(model: DetailModel) = DetailPresenter(model, AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    fun provideModel(api: MoviesApi, dao: MovieDetailDao): DetailModel
            = DetailModelRetrofit(api, API_KEY, dao, Schedulers.io(), AndroidSchedulers.mainThread())
}