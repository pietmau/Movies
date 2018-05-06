package com.pppp.movies.search.di

import com.pppp.movies.search.view.SearchFragment
import dagger.Subcomponent

@MainActivityScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainSubComponent {
    fun inject(searchFragment: SearchFragment)
}