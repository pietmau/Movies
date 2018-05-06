package com.pppp.movies.main.presenter

import com.pppp.movies.main.view.MainView

class MainPresenter {
    private var view: MainView? = null

    fun bind(mainActivity: MainView) {
        this.view = mainActivity
    }

    fun unbind() {
        view = null
    }

    fun onSearchRequested() {
        view?.navigateToSearchScreen()
    }
}