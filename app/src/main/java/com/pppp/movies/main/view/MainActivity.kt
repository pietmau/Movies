package com.pppp.movies.main.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.pppp.movies.R
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.application.App
import com.pppp.movies.detail.view.DetailActivity
import com.pppp.movies.isVisible
import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.viewmodel.MainPresenter
import com.pppp.movies.main.viewmodel.MainView
import com.pppp.movies.show
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
    companion object {
        private const val PROGRESS_IS_SHOWING = "progress_is_showing"
    }

    @Inject lateinit var viewModel: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).applicationComponent.with(MainModule(this)).inject(this)
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
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.MOVIE_KEY_FROM_NETWORK, movie)
        startActivity(detailIntent)
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

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(PROGRESS_IS_SHOWING,progress.isVisible)
        super.onSaveInstanceState(outState)
    }

    private fun restoreState(savedInstanceState: Bundle?) {
        val show = savedInstanceState?.getBoolean(PROGRESS_IS_SHOWING)
        showProgress(show ?: false)
    }
}

