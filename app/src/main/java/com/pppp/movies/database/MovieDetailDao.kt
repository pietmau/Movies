package com.pppp.movies.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.pppp.movies.apis.detail.MovieDetail
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM moviedetail WHERE id = (:id)")
    fun getAsFlowable(id: Int): Flowable<MovieDetail>

    @Query("SELECT * FROM moviedetail WHERE id = (:id)")
    fun getAsSingle(id: Int): Single<MovieDetail>

    @Query("SELECT * FROM moviedetail WHERE isFavourite = 1")
    fun getAllFavourites(): Flowable<List<MovieDetail>>

    @Insert
    fun insert(movie: MovieDetail)

    @Update
    fun update(movie: MovieDetail)
}