package com.pppp.movies.favourite.model

import com.pppp.movies.apis.search.Movie
import com.pppp.movies.database.MovieDetailDao
import io.reactivex.Observable

class RoomFavouriteModel(
        private val dao: MovieDetailDao,
        private val mapper: MovieMapper
) : FavouriteModel {

    override fun subscribe(): Observable<List<Movie>> {
        return dao.getAllFavourites()
                .toObservable()
                .map { mapper.mapToMovie(it) }
    }

}