package com.pppp.movies.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.pppp.movies.apis.detail.MovieDetail


@Database(entities = arrayOf(MovieDetail::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDetailDao(): MovieDetailDao

    companion object {
        private var db: AppDatabase? = null

        fun getDb(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(context, AppDatabase::class.java, "database-name").build()
            }
            return db!!
        }
    }
}