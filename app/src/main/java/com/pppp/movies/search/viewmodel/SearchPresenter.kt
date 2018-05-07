package com.pppp.movies.search.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import com.jakewharton.rxrelay2.Relay
import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.search.view.custom.MovieSearchResultAdapter
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

class SearchPresenter(
        private val subject: Relay<PagedList<Movie>>,
        private val pagedListFactory: PagedListFactory

) : ViewModel(), MovieSearchResultAdapter.Callback {

    private var view: SearchView? = null
    private var currentQuery: String? = null
    private var disposable: Disposable? = null
    private var pagedItemsFlowable: Flowable<PagedList<Movie>>? = pagedListFactory.getPagesList()

    fun onQueryTextChange(newQuery: String?): Boolean {
        if (newQuery == null || !isAValidQuery(newQuery)) {
            return true
        }
        showProgress(true)
        currentQuery = newQuery
        pagedItemsFlowable = pagedListFactory.getPagesList(newQuery)
        Observable.just(pagedItemsFlowable)
                .flatMap { it.toObservable() }
                .doOnError { error -> this@SearchPresenter.onError(error) }
                .onErrorResumeNext(Observable.empty())
                .subscribe(subject)
        return true
    }

    fun subscribe(searchView: SearchView) {
        this.view = searchView
        disposable = subject.subscribeWith(object : SimpleObserver<PagedList<Movie>>() {
            override fun onError(throwable: Throwable) {
                this@SearchPresenter.onError(throwable)
            }

            override fun onNext(movies: PagedList<Movie>) {
                this@SearchPresenter.onMoviesAvailable(movies)
            }
        })
    }

    private fun onMoviesAvailable(movies: PagedList<Movie>) {
        showProgress(false)
        view?.onMovieAvailable(movies)
    }

    fun onError(throwable: Throwable) {
        showProgress(false)
        view?.onError(throwable)
    }

    fun unSubscribe() {
        view = null
        disposable?.dispose()
    }

    override fun onMovieSelected(movie: Movie) {
        view?.startDetailScreen(movie)
    }

    private fun showProgress(show: Boolean) {
        view?.showProgress(show)
    }

    private fun isAValidQuery(newText: String) = !newText.isBlank() && !newText.equals(currentQuery, true)
}