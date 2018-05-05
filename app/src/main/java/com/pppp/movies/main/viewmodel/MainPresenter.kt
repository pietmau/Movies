package com.pppp.movies.main.viewmodel

import android.arch.lifecycle.ViewModel
import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.apis.search.MoviesSearchResult
import com.pppp.movies.main.model.MainModel
import com.pppp.movies.main.view.custom.MovieSearchResultAdapter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

class MainPresenter(
        private val model: MainModel,
        private val mainThreadScheduler: Scheduler,
        private val workerThreadScheduler: Scheduler
) : ViewModel(), MovieSearchResultAdapter.Callback {

    private val subject: Subject<MoviesSearchResult> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    var view: MainView? = null

    fun onQueryTextChange(newText: String?): Boolean {
        if (newText == null || newText.isBlank()) {
            return true
        }
        showProgress(true)
        compositeDisposable
                .add(model.search(newText)
                        .subscribeOn(workerThreadScheduler)
                        .observeOn(mainThreadScheduler)
                        .doOnError { throwable -> subject.onError(throwable) }//TODO review!!!!!!!!!!!!
                        .doOnNext { searchResult -> subject.onNext(searchResult) }//TODO review!!!!!!!!!!!!
                        .subscribe({}, {}))
        return true
    }

    fun subscribe(mainView: MainView) {
        this.view = mainView
        compositeDisposable
                .add(subject
                        .subscribeWith(object : SimpleObserver<MoviesSearchResult>() {
                            override fun onError(throwable: Throwable) {
                                this@MainPresenter.onError(throwable)
                            }

                            override fun onNext(moviesSearchResult: MoviesSearchResult) {
                                this@MainPresenter.onMovieAvailable(moviesSearchResult)
                            }
                        }))
    }

    private fun onMovieAvailable(moviesSearchResult: MoviesSearchResult) {
        showProgress(false)
        moviesSearchResult.movies?.let { view?.onMovieAvailable(moviesSearchResult.movies) }
    }

    private fun onError(throwable: Throwable) {
        showProgress(false)
        view?.onError(throwable)
    }

    fun unSubscribe() {
        compositeDisposable.dispose()
        view = null
    }

    override fun onItemClicked(movie: Movie) {
        view?.startDetailScreen(movie)
    }

    private fun showProgress(show: Boolean) {
        view?.showProgress(show)
    }

}