package com.pppp.movies.favourite.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.movies.R
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.application.MoviesApp
import com.pppp.movies.detail.view.DetailActivity
import com.pppp.movies.favourite.di.FavouriteModule
import com.pppp.movies.favourite.presenter.FavouritePresenter
import kotlinx.android.synthetic.main.fragment_favourites.*
import javax.inject.Inject

class FavouriteFragment : Fragment(), FavouriteView {
    @Inject lateinit var presnter: FavouritePresenter

    companion object {
        val TAG = FavouriteFragment::class.simpleName
        fun newInstance() = FavouriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favourites, container, false)
        (activity?.application as? MoviesApp)?.applicationComponent?.with(FavouriteModule())?.inject(this)
        activity?.setTitle(getString(R.string.favourites))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyler.setCallback(presnter)
    }

    override fun onPause() {
        super.onPause()
        presnter.unsubscribe()
    }

    override fun onResume() {
        super.onResume()
        presnter.subscribe(this)
    }

    override fun onError(throwable: Throwable) {
        Snackbar.make(root, throwable.localizedMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onMoviesRetrieved(movies: List<Movie>) {
        recyler.setMovies(movies)
    }

    override fun showDetaiScreen(movie: Movie) {
        activity?.let {
            val intent = Intent(it, DetailActivity::class.java)
            intent.putExtra(DetailActivity.MOVIE_KEY_FROM_DB, movie)
            startActivity(intent)
        }
    }

}