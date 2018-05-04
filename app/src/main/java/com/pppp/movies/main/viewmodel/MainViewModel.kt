package com.pppp.movies.main.viewmodel

import com.pppp.movies.apis.search.MoviesSearchResult
import com.pppp.movies.main.model.MainModel
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

class MainViewModel(
        private val model: MainModel,
        private val mainThreadScheduler: Scheduler,
        private val workerThreadScheduler: Scheduler
) {
    private val subject: Subject<MoviesSearchResult> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun onQueryTextChange(newText: String?): Boolean {
        newText?.let {
            compositeDisposable
                    .add(model.search(it)
                            .subscribeOn(workerThreadScheduler)
                            .observeOn(mainThreadScheduler)
                            .doOnNext { searchResult -> subject.onNext(searchResult) }
                            .subscribe({}, {}))
        }
        return true
    }

    fun subscribe(observer: DisposableObserver<MoviesSearchResult>) {
        compositeDisposable
                .add(subject
                        .subscribeWith(observer))
    }

    fun unSubscribe() {
        compositeDisposable.dispose()
    }


}