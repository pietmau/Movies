package com.pppp.movies.main.view


import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.pppp.movies.R
import com.pppp.movies.application.MoviesApp
import com.pppp.movies.main.di.MainModule
import com.pppp.movies.main.presenter.MainPresenter
import com.pppp.movies.search.view.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, MainView {
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        (applicationContext as MoviesApp).applicationComponent.with(MainModule()).inject(this)
        savedInstanceState?:navigateToSearchScreen()
    }

    override fun onPause() {
        super.onPause()
        presenter.unbind()
    }

    override fun onResume() {
        super.onResume()
        presenter.bind(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                presenter.onSearchRequested()
            }
            R.id.favourites -> {

            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun navigateToSearchScreen() {
        val frag = supportFragmentManager.findFragmentByTag(SearchFragment.TAG) ?: SearchFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.container, frag, SearchFragment.TAG).commit()
    }


}
