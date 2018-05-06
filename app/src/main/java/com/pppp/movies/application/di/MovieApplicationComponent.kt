package com.pppp.movies.application.di

import com.pppp.movies.detail.di.DetailModule
import com.pppp.movies.detail.di.DetailSubComponent
import com.pppp.movies.favourite.di.FavouriteModule
import com.pppp.movies.favourite.di.FavouriteSubComponent
import com.pppp.movies.imageloader.ImageLoader
import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.di.MainSubComponent
import com.pppp.movies.search.di.SearchModule
import com.pppp.movies.search.di.SearchSubComponent
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface MovieApplicationComponent {

    fun imageLoader(): ImageLoader

    fun with(searchModule: SearchModule): SearchSubComponent

    fun with(mainModule: DetailModule): DetailSubComponent

    fun with(mainModule: MainModule): MainSubComponent

    fun with(favouriteModule: FavouriteModule): FavouriteSubComponent

}