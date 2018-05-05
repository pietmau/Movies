package com.pppp.movies.detail.di

import com.pppp.movies.detail.view.DetailActivity
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(DetailModule::class))
interface DetailSubComponent {

    fun inject(detailActivity: DetailActivity)
}