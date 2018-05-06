package com.pppp.movies.detail.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.pppp.movies.R
import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.application.MoviesApp
import com.pppp.movies.detail.di.DetailModule
import com.pppp.movies.detail.presenter.DetailPresenter
import com.pppp.movies.findDrawable
import com.pppp.movies.getMovie
import com.pppp.movies.imageloader.ImageLoader
import kotlinx.android.synthetic.main.detail_activity.*
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), DetailsView {
    companion object {
        const val MOVIE_KEY_FROM_NETWORK = "movie_from_network"
        const val MOVIE_KEY_FROM_DB = "movie_from_db"
    }

    @Inject lateinit var presenter: DetailPresenter
    @Inject lateinit var loader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        (application as MoviesApp).applicationComponent.with(DetailModule()).inject(this)
        when {
            (getMovie(MOVIE_KEY_FROM_NETWORK) != null) -> presenter.getDetailsFromNet(getMovie(MOVIE_KEY_FROM_NETWORK))
            (getMovie(MOVIE_KEY_FROM_DB) != null) -> TODO()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe(this)
    }

    override fun onPause() {
        super.onPause()
        presenter.unSubscribe()
    }

    override fun onError(throwable: Throwable) = Snackbar.make(root, throwable.localizedMessage, Snackbar.LENGTH_LONG).show()

    override fun onDetailsAvailable(detail: MovieDetail) {
        movie_title.text = detail.title
        overview.text = detail.overview
        favourite.setImageDrawable(getfavouriteDrawable(detail.isFavourite))
        loader.loadPoster(detail.posterPath, image)
        favourite.setOnClickListener { presenter.onFavouritePressed() }
    }

    private fun getfavouriteDrawable(isFavourite: Boolean) =
            if (isFavourite) findDrawable(R.drawable.ic_favorite_filled_24dp) else findDrawable(R.drawable.ic_favorite_border_24dp)


}

