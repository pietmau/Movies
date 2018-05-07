package com.pppp.movies

import android.graphics.drawable.ColorDrawable
import android.support.test.espresso.UiController
import android.support.test.espresso.ViewAction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.view.View
import android.widget.ProgressBar
import org.hamcrest.Matcher

fun replaceProgressBarDrawable(): ViewAction {
    return ViewActions.actionWithAssertions(object : ViewAction {
        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isAssignableFrom(ProgressBar::class.java)
        }

        override fun getDescription(): String {
            return "replace the ProgressBar drawable"
        }

        override fun perform(uiController: UiController, view: View) {
            val progressBar = view as ProgressBar
            progressBar.indeterminateDrawable = ColorDrawable(-0x10000)
            uiController.loopMainThreadUntilIdle()
        }
    })
}