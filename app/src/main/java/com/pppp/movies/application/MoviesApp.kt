package com.pppp.movies.application

import android.app.Application
import com.pppp.movies.application.di.ApplicationModule
import com.pppp.movies.application.di.DaggerMovieApplicationComponent
import com.pppp.movies.application.di.MovieApplicationComponent


class MoviesApp : Application() {
    lateinit var applicationComponent: MovieApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerMovieApplicationComponent.builder().applicationModule(ApplicationModule(this.applicationContext)).build()
    }
}