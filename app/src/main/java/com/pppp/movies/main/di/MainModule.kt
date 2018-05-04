package com.pppp.movies.main.di

import com.pppp.movies.main.presenter.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    fun providesPresenter(): MainPresenter = MainPresenter()
}