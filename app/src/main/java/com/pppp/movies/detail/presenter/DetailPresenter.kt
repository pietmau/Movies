package com.pppp.movies.detail.presenter

import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.detail.model.DetailModel
import com.pppp.movies.detail.view.DetailsView
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject

class DetailPresenter(private val model: DetailModel,
                      private val mainThreadScheduler: Scheduler,
                      private val workerThreadScheduler: Scheduler) {
    private val subject: Subject<MovieDetail> = BehaviorSubject.create()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var view: DetailsView? = null

    fun getDetailsFromNet(movie: Movie) {
        compositeDisposable.add(model.getMovieDetail(movie.id)
                .subscribeOn(workerThreadScheduler)
                .observeOn(mainThreadScheduler)
                .doOnNext { detail -> subject.onNext(detail) }
                .doOnError { throwable -> subject.onError(throwable) }
                .subscribe({}, {}))
    }

    fun subscribe(view: DetailsView) {//TDO do the same for main view
        this.view = view
        compositeDisposable.add(subject.subscribeWith(object : SimpleObserver<MovieDetail>() {
            override fun onError(throwable: Throwable) {
                this@DetailPresenter.onError(throwable)
            }

            override fun onNext(detail: MovieDetail) {
                onDetailsAvailable(detail)
            }
        }))
    }

    private fun onError(throwable: Throwable) {
        view?.onError(throwable)
    }

    private fun onDetailsAvailable(detail: MovieDetail) {
        view?.onDetailsAvailable(detail)
    }

    fun unSubscribe() {
        compositeDisposable.dispose()
    }

    fun onFavouritePressed() {
        TODO()
    }


}