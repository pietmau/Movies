package com.pppp.movies.application

import android.app.Application
import android.os.StrictMode
import com.pppp.movies.BuildConfig
import com.pppp.movies.application.di.ApplicationModule
import com.pppp.movies.application.di.DaggerMovieApplicationComponent
import com.pppp.movies.application.di.MovieApplicationComponent

import com.squareup.leakcanary.LeakCanary


class MoviesApp : Application() {
    lateinit var applicationComponent: MovieApplicationComponent

    override fun onCreate() {
        super.onCreate()
        setupStricktMode()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        applicationComponent = DaggerMovieApplicationComponent.builder().applicationModule(ApplicationModule(this.applicationContext)).build()
    }

    private fun setupStricktMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .penaltyDeath()
                    .build())
            StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder()
                    .detectActivityLeaks()
                    .penaltyLog()
                    .penaltyDeath()
                    .build())
        }
    }
}