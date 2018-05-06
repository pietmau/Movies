package com.pppp.movies.favourite.model

import com.pppp.movies.apis.detail.MovieDetail
import com.pppp.movies.apis.search.Movie

interface MovieMapper {

    fun mapToMovie(movieDetail: List<MovieDetail>): List<Movie> = movieDetail.map { toMovie(it) }

    fun toMovie(detail: MovieDetail): Movie =
            Movie(
                    detail.voteCount,
                    detail.id,
                    detail.isVideo,
                    detail.voteAverage,
                    detail.title,
                    detail.popularity,
                    detail.posterPath,
                    detail.originalLanguage,
                    detail.originalTitle,
                    detail.backdropPath,
                    detail.isAdult,
                    detail.overview
            )
}