package com.pppp.movies.detail.model

import com.pppp.movies.API_KEY_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.database.MovieDetailDao
import io.reactivex.Observable
import io.reactivex.Scheduler

class DetailRepositoryRetrofit(
        private val api: MoviesApi,
        private val apiKey: String,
        private val dao: MovieDetailDao,
        val schedulerIo: Scheduler,
        val mainThread: Scheduler) : DetailRepository {

    override fun getMovieDetail(id: Int): Observable<MovieDetail> {
        val map = HashMap<String, String>()
        map.put(API_KEY_KEY, apiKey)
        // First we try to get it from the db
        return dao.getAsSingle(id)
                .toObservable()
                // if it is not in the db
                .onErrorResumeNext { throwable: Throwable ->
                    // we get it form the network
                    api.getDetail(id, map).map { movie ->
                        // and we put it in the db
                        dao.insert(movie)
                        movie
                    }
                }
                // finally we get it from the db as a Flowable,
                // so that Room can update it if needed
                .map { dao.getAsFlowable(id).toObservable() }
                .flatMap { it }
    }

    override fun getMovieDetailFromDb(id: Int): Observable<MovieDetail> = dao.getAsFlowable(id).toObservable()

    override fun changeFavourite(movieDetail: MovieDetail) {
        Observable.fromCallable {
            dao.update(movieDetail)
        }
                .subscribeOn(schedulerIo)
                .observeOn(mainThread)
                .subscribe({}, {})
    }

}