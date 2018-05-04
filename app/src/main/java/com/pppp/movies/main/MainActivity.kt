package com.pppp.movies.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.movies.R
import com.pppp.movies.application.App
import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.presenter.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).applicationComponent.with(MainModule()).inject(this)
        search.setOnQueryTextListener(object : SimpleOnQueryTextListener() {
            override fun onQueryTextChange(newText: String?): Boolean {
                return viewModel.onQueryTextChange(newText)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        viewModel.unSubscribe()
    }

    override fun onResume() {
        super.onResume()
        viewModel.subscribe()
    }
}
