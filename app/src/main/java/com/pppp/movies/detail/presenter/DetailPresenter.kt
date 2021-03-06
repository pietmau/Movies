package com.pppp.movies.detail.presenter

import com.jakewharton.rxrelay2.BehaviorRelay
import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.detail.model.DetailRepository
import com.pppp.movies.detail.view.DetailsView
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class DetailPresenter(private val repository: DetailRepository,
                      private val mainThreadScheduler: Scheduler,
                      private val workerThreadScheduler: Scheduler) {
    private val subject: BehaviorRelay<MovieDetail> = BehaviorRelay.create<MovieDetail>()
    private var compositeDisposable: CompositeDisposable = CompositeDisposable()
    private var view: DetailsView? = null
    private var detail: MovieDetail? = null

    fun getDetailsFromNet(movie: Movie) {
        compositeDisposable.add(repository.getMovieDetail(movie.id)
                .subscribeOn(workerThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(subject))
    }

    fun subscribe(view: DetailsView) {
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
        this.detail = detail
        view?.onDetailsAvailable(detail)
    }

    fun unSubscribe() {
        compositeDisposable.dispose()
    }

    fun onFavouritePressed() {
        detail?.let { detail ->
            detail.isFavourite = !detail.isFavourite
            repository.changeFavourite(detail)
        }
    }

    fun getDetailsFromDb(movie: Movie) {
        compositeDisposable.add(repository.getMovieDetailFromDb(movie.id)
                .subscribeOn(workerThreadScheduler)
                .observeOn(mainThreadScheduler)
                .subscribe(subject))
    }


}