package com.pppp.movies.search.viewmodel

import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.apis.search.MoviesSearchResult
import com.pppp.movies.search.model.SearchModel
import com.pppp.movies.search.view.custom.MovieSearchResultAdapter
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable

class SearchPresenter(
        private val model: SearchModel,
        private val mainThreadScheduler: Scheduler,
        private val workerThreadScheduler: Scheduler
) : ViewModel(), MovieSearchResultAdapter.Callback, Observer<MoviesSearchResult> {
    private val subject: Relay<MoviesSearchResult> = BehaviorRelay.create<MoviesSearchResult>()
    var view: SearchView? = null
    var query: String? = null

    fun onQueryTextChange(newText: String?): Boolean {
        if (newText == null || newText.isBlank() || newText.equals(query, true)) {
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
        subject.subscribe(this)
    }

    override fun onError(throwable: Throwable) {
        showProgress(false)
        view?.onError(throwable)
    }

    // No need to unsubscribe from the model or the subject,
    // because the only thing that will leak is the presenter itself,
    // but we are preserving the presenter across config changes on purpose to avoid restarting the same
    // queries
    fun unSubscribe() {
        view = null
    }

    override fun onSubscribe(d: Disposable) {}

    override fun onComplete() {

    }

    override fun onNext(t: MoviesSearchResult) {
        showProgress(false)
        t.movies?.let { view?.onMovieAvailable(it) }
    }

    override fun onItemClicked(movie: Movie) {
        view?.startDetailScreen(movie)
    }

    private fun showProgress(show: Boolean) {
        view?.showProgress(show)
    }

}