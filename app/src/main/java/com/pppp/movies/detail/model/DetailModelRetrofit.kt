package com.pppp.movies.detail.model

import com.pppp.movies.API_KEY_KEY
import com.pppp.movies.apis.MoviesApi
import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.database.MovieDetailDao
import io.reactivex.Observable
import io.reactivex.Scheduler

class DetailModelRetrofit(
        private val api: MoviesApi,
        private val apiKey: String,
        private val dao: MovieDetailDao,
        val schedulerIo: Scheduler,
        val mainThread: Scheduler) : DetailModel {

    override fun getMovieDetailFromNewtwork(id: Int): Observable<MovieDetail> {
        val map = HashMap<String, String>()
        map.put(API_KEY_KEY, apiKey)
        return api.getDetail(id, map)
                .doOnNext {
                    try {
                        dao.insert(it)
                    } catch (exception: Exception) {
                    }
                }
                .map { dao.get(id).toObservable() }
                .flatMap { it }
    }

    override fun getMovieDetailFromDb(id: Int): Observable<MovieDetail> = dao.get(id).toObservable()


    override fun onFavouritePressed(movieDetail: MovieDetail) {
        Observable.fromCallable {
            dao.update(movieDetail)
        }
                .subscribeOn(schedulerIo)
                .observeOn(mainThread)
                .subscribe({}, {})
    }

}