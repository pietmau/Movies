package com.pppp.movies.main.di

import com.pppp.movies.main.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSubComponent {
    fun inject(mainActivity: MainActivity)
}