package com.pppp.movies.main.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.movies.R
import com.pppp.movies.apis.SimpleObserver
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.apis.search.MoviesSearchResult
import com.pppp.movies.application.App
import com.pppp.movies.detail.view.DetailActivity
import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.viewmodel.MainPresenter
import com.pppp.movies.main.viewmodel.MainView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {
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
    }

    override fun onPause() {
        super.onPause()
        viewModel.unSubscribe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.subscribe(object : SimpleObserver<MoviesSearchResult>() {
            override fun onNext(result: MoviesSearchResult) {
                result.movies?.let { recycler.setData(it) }
            }
        }, this)
    }

    override fun startDetailScreen(movie: Movie) {
        val detailIntent = Intent(this, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.MOVIE_KEY_FROM_NETWORK, movie)
        startActivity(detailIntent)
    }
}
