package com.pppp.movies.search.di

import com.pppp.movies.search.view.SearchFragment
import dagger.Subcomponent

@SearchScope
@Subcomponent(modules = arrayOf(SearchModule::class))
interface SearchSubComponent {
    fun inject(searchFragment: SearchFragment)
}