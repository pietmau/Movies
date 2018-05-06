package com.pppp.movies.application.di

import com.pppp.movies.detail.di.DetailModule
import com.pppp.movies.detail.di.DetailSubComponent
import com.pppp.movies.imageloader.ImageLoader
import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.di.MainSubComponent
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface MovieApplicationComponent {

    fun imageLoader(): ImageLoader

    fun with(mainModule: MainModule): MainSubComponent

    fun with(mainModule: DetailModule): DetailSubComponent

}