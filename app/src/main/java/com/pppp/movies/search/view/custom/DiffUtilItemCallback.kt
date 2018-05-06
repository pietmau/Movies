package com.pppp.movies.search.view.custom

import android.support.v7.util.DiffUtil
import com.pppp.movies.apis.search.Movie

class DiffUtilItemCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areContentsTheSame(oldItem: Movie?, newItem: Movie?): Boolean {
        if (oldItem == null || newItem == null) {
            return false
        }
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Movie?, newItem: Movie?): Boolean {
        if (oldItem == null || newItem == null) {
            return false
        }
        return oldItem.id == newItem.id
    }
}