package com.pppp.movies.favourite.di

import com.pppp.movies.database.MovieDetailDao
import com.pppp.movies.favourite.model.FavouriteModel
import com.pppp.movies.favourite.model.MovieMapperImpl
import com.pppp.movies.favourite.model.RoomFavouriteModel
import com.pppp.movies.favourite.presenter.FavouritePresenter
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module
class FavouriteModule {

    @Provides
    fun providePresenter(model: FavouriteModel) = FavouritePresenter(model, Schedulers.io(), AndroidSchedulers.mainThread())

    @Provides
    fun provideModel(dao: MovieDetailDao): FavouriteModel = RoomFavouriteModel(dao, MovieMapperImpl())//TODO Rename other model as well

}