package com.pppp.movies.favourite.di

import com.pppp.movies.favourite.view.FavouriteFragment
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(FavouriteModule::class))
interface FavouriteSubComponent {

    fun inject(favouriteFragment: FavouriteFragment)
}