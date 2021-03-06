package com.pppp.movies.search.view

import android.arch.paging.PagedList
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
import com.pppp.movies.isVisible
import com.pppp.movies.search.di.SearchModule
import com.pppp.movies.search.viewmodel.SearchPresenter
import com.pppp.movies.search.viewmodel.SearchView
import com.pppp.movies.show
import kotlinx.android.synthetic.main.fragment_search.*
import javax.inject.Inject

class SearchFragment : Fragment(), SearchView {
    companion object {
        private const val PROGRESS_IS_SHOWING = "progress_is_showing"
        val TAG = SearchFragment::class.simpleName
        fun newInstance() = SearchFragment()
    }

    @Inject lateinit var presenter: SearchPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        activity?.setTitle(getString(R.string.search))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let { (it.application as MoviesApp).applicationComponent.with(SearchModule(it)).inject(this) }
        search.setOnQueryTextListener(object : SimpleOnQueryTextListener() {
            override fun onQueryTextChange(newText: String?): Boolean {
                return presenter.onQueryTextChange(newText)
            }
        })
        recycler.setCallback(presenter)
        restoreState(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun startDetailScreen(movie: Movie) {
        activity?.let {
            val detailIntent = Intent(it, DetailActivity::class.java)
            detailIntent.putExtra(DetailActivity.MOVIE_KEY_FROM_NETWORK, movie)
            startActivity(detailIntent)
        }
    }

    override fun onError(throwable: Throwable) {
        Snackbar.make(root, throwable.localizedMessage, Snackbar.LENGTH_LONG).show()
    }

    override fun onMovieAvailable(movies: PagedList<Movie>) {
        recycler.submitData(movies)
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

