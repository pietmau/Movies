package com.pppp.movies.search.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.search.model.DataSourceFactory
import com.pppp.movies.search.model.SearchModel
import com.pppp.movies.search.view.custom.MovieSearchResultAdapter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SearchPresenter(
        private val model: SearchModel,
        private val mainThreadScheduler: Scheduler,//TODO
        private val workerThreadScheduler: Scheduler
) : ViewModel(), MovieSearchResultAdapter.Callback {
    private val subject: Relay<PagedList<Movie>> = BehaviorRelay.create<PagedList<Movie>>()
    var view: SearchView? = null
    var query: String? = null
    private var disposable: Disposable? = null

    var pagedItems: Flowable<PagedList<Movie>>? = RxPagedListBuilder(DataSourceFactory(model, ""), 20)
            .setFetchScheduler(Schedulers.io())
            .buildFlowable(BackpressureStrategy.LATEST)

    fun onQueryTextChange(newText: String?): Boolean {
        if (newText == null || !isAValidQuery(newText)) {
            return true
        }
        query = newText
        pagedItems = RxPagedListBuilder(DataSourceFactory(model, newText), 20)
                .setFetchScheduler(Schedulers.io())
                .buildFlowable(BackpressureStrategy.LATEST)
        Observable.just(pagedItems)
                .flatMap { it.toObservable() }
                .subscribe(subject)
        showProgress(true)
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

    override fun onItemClicked(movie: Movie) {
        view?.startDetailScreen(movie)
    }

    private fun showProgress(show: Boolean) {
        view?.showProgress(show)
    }

    private fun isAValidQuery(newText: String) = !newText.isBlank() && !newText.equals(query, true)
}