package com.pppp.movies.application.di

import android.content.Context
import com.pppp.movies.BASE_IMAGE_URL
import com.pppp.movies.apis.MovieService
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.database.AppDatabase
import com.pppp.movies.database.MovieDetailDao
import com.pppp.movies.imageloader.ImageLoader
import com.pppp.movies.imageloader.PicassoImageLoader
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    fun provideImageLoader(picasso: Picasso): ImageLoader = PicassoImageLoader(picasso, BASE_IMAGE_URL)

    @Provides
    fun providesApi(): MoviesApi = MovieService

    @Provides
    fun providePicasso() = getPicasso()

    @Provides
    fun provideDatabase(): MovieDetailDao = AppDatabase.getDb(context).movieDetailDao()

    private fun getPicasso(): Picasso = Picasso.get()

}