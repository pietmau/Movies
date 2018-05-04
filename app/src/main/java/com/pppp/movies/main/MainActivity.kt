package com.pppp.movies.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.pppp.movies.R
import com.pppp.movies.application.App
import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as App).applicationComponent.with(MainModule()).inject(this)
        search.setOnQueryTextListener(object : SimpleOnQueryTextListener() {
            override fun onQueryTextChange(newText: String?): Boolean {
                return presenter.onQueryTextChange(newText)
            }
        })
    }
}
