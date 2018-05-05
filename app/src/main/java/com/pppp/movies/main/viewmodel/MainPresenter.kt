package com.pppp.movies.main.viewmodel

import android.arch.lifecycle.ViewModel
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.apis.search.MoviesSearchResult
import com.pppp.movies.main.model.MainModel
import com.pppp.movies.main.view.custom.MovieSearchResultAdapter
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
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
        newText ?: return true
        compositeDisposable
                .add(model.search(newText)
                        .subscribeOn(workerThreadScheduler)
                        .observeOn(mainThreadScheduler)
                        .doOnNext { searchResult -> subject.onNext(searchResult) }//TODO review!!!!!!!!!!!!
                        .subscribe({ }, {}))
        return true
    }

    fun subscribe(observer: DisposableObserver<MoviesSearchResult>, mainView: MainView) {
        this.view = mainView
        compositeDisposable
                .add(subject
                        .subscribeWith(observer))
    }

    fun unSubscribe() {
        compositeDisposable.dispose()
        view = null
    }

    override fun onItemClicked(movie: Movie) {
        view?.startDetailScreen(movie)
    }


}