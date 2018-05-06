package com.pppp.movies.search.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pppp.movies.R
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.application.App
import com.pppp.movies.detail.view.DetailActivity
import com.pppp.movies.isVisible
import com.pppp.movies.search.di.MainModule
import com.pppp.movies.search.viewmodel.MainPresenter
import com.pppp.movies.search.viewmodel.MainView
import com.pppp.movies.show
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment(), MainView {
    companion object {
        private const val PROGRESS_IS_SHOWING = "progress_is_showing"
    }
    @Inject lateinit var viewModel: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.let { (it as App).applicationComponent.with(MainModule(it)).inject(this) }
        search.setOnQueryTextListener(object : SimpleOnQueryTextListener() {
            override fun onQueryTextChange(newText: String?): Boolean {
                return viewModel.onQueryTextChange(newText)
            }
        })
        recycler.setCallback(viewModel)
        restoreState(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        viewModel.unSubscribe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.subscribe(this)
    }

    override fun startDetailScreen(movie: Movie) {//TODO exprenzlize in extensions
        activity?.let {
            val detailIntent = Intent(it, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.MOVIE_KEY_FROM_NETWORK, movie)
            startActivity(detailIntent)
        }
    }

    override fun onError(throwable: Throwable) {
        Snackbar.make(root, throwable.localizedMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onMovieAvailable(movies: List<Movie>) {
        recycler.setData(movies)
    }

    override fun showProgress(show: Boolean) {
        progress.show(show)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.putBoolean(PROGRESS_IS_SHOWING, progress.isVisible)
        super.onSaveInstanceState(outState)
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        val show = savedInstanceState?.getBoolean(PROGRESS_IS_SHOWING)
        showProgress(show ?: false)
    }
}

