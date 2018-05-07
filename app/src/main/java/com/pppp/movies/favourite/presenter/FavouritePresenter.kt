package com.pppp.movies.favourite.presenter

import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.favourite.model.FavouriteModel
import com.pppp.movies.favourite.view.FavouriteView
import com.pppp.movies.search.view.custom.MovieSearchResultAdapter
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

class FavouritePresenter(
        private val model: FavouriteModel,
        private val workerThread: Scheduler,
        private val mainThread: Scheduler
) : MovieSearchResultAdapter.Callback {
    private var view: FavouriteView? = null
    private var disposable: Disposable? = null

    fun unsubscribe() {
        disposable?.dispose()
        view = null
    }

    fun subscribe(view: FavouriteView) {
        this.view = view
        disposable = model.subscribe()
                .subscribeOn(workerThread)
                .observeOn(mainThread)
                .subscribeWith(object : SimpleObserver<List<Movie>>() {
                    override fun onError(throwable: Throwable) {
                        this@FavouritePresenter.onError(throwable)
                    }

                    override fun onNext(movies: List<Movie>) {
                        this@FavouritePresenter.onMoviesRetrieved(movies)
                    }
                })
    }

    private fun onMoviesRetrieved(movies: List<Movie>) {
        view?.onMoviesRetrieved(movies)
    }

    private fun onError(throwable: Throwable) {
        view?.onError(throwable)
    }

    override fun onMovieSelected(movie: Movie) {
        view?.showDetaiScreen(movie)
    }

}