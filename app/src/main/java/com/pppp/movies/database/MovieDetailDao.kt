package com.pppp.movies.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.pppp.movies.apis.detail.MovieDetail
import io.reactivex.Flowable


@Dao
interface MovieDetailDao {
    @Query("SELECT * FROM moviedetail WHERE id = (:id)")
    fun get(id: Int): Flowable<MovieDetail>

    @Insert
    fun insert(movie: MovieDetail)

    @Update
    fun update(movie: MovieDetail)
}