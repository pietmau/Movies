package com.pppp.movies.application

import android.app.Application
import android.os.StrictMode
import com.pppp.movies.BuildConfig
import com.pppp.movies.application.di.ApplicationComponent
import com.pppp.movies.application.di.DaggerApplicationComponent
import com.squareup.leakcanary.LeakCanary


class App : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        setupStricktMode()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        applicationComponent = DaggerApplicationComponent.create()
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