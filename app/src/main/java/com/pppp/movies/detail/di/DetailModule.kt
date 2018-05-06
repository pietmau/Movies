package com.pppp.movies.detail.di


import com.pppp.movies.API_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.database.MovieDetailDao
import com.pppp.movies.detail.model.DetailRepository
import com.pppp.movies.detail.model.DetailRepositoryRetrofit
import com.pppp.movies.detail.presenter.DetailPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class DetailModule {

    @Provides
    fun providePresenter(repository: DetailRepository) = DetailPresenter(repository, AndroidSchedulers.mainThread(), Schedulers.io())

    @Provides
    fun provideModel(api: MoviesApi, dao: MovieDetailDao): DetailRepository
            = DetailRepositoryRetrofit(api, API_KEY, dao, Schedulers.io(), AndroidSchedulers.mainThread())
}