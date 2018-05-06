package com.pppp.movies.search.viewmodel

import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.apis.search.MoviesSearchResult
import com.pppp.movies.search.model.SearchModel
import com.pppp.movies.search.view.custom.MovieSearchResultAdapter
import io.reactivex.Scheduler

class SearchPresenter(
        private val model: SearchModel,
        private val mainThreadScheduler: Scheduler,
        private val workerThreadScheduler: Scheduler
) : ViewModel(), MovieSearchResultAdapter.Callback {
    private val subject: Relay<MoviesSearchResult> = BehaviorRelay.create<MoviesSearchResult>()
    var view: SearchView? = null
    var query: String? = null
    private var disposable: SimpleObserver<MoviesSearchResult>? = null

    fun onQueryTextChange(newText: String?): Boolean {
        if (newText == null || !isAValidQuery(newText)) {
            return true
        }
        query = newText
        showProgress(true)
        model.search(newText)
                .subscribeOn(workerThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(subject)
        return true
    }

    fun subscribe(searchView: SearchView) {
        this.view = searchView
        disposable = subject.subscribeWith(object : SimpleObserver<MoviesSearchResult>() {
            override fun onError(throwable: Throwable) {
                this@SearchPresenter.onError(throwable)
            }

            override fun onNext(moviesSearchResult: MoviesSearchResult) {
                this@SearchPresenter.onMovieAvailable(moviesSearchResult)
            }
        })

    }

    fun onError(throwable: Throwable) {
        showProgress(false)
        view?.onError(throwable)
    }

    fun unSubscribe() {
        view = null
        disposable?.dispose()
    }

    fun onMovieAvailable(moviesSearchResult: MoviesSearchResult) {
        showProgress(false)
        moviesSearchResult.movies?.let { view?.onMovieAvailable(it) }
    }

    override fun onItemClicked(movie: Movie) {
        view?.startDetailScreen(movie)
    }

    private fun showProgress(show: Boolean) {
        view?.showProgress(show)
    }

    private fun isAValidQuery(newText: String) = !newText.isBlank() && !newText.equals(query, true)
}