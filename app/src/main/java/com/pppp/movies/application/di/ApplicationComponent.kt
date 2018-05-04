package com.pppp.movies.application.di

import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.di.MainSubComponent
import dagger.Component

@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun with(mainModule: MainModule): MainSubComponent
}