package com.pppp.movies

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.pppp.movies.main.view.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchTest {
    private val TEXT: String = "t"

    @get:Rule
    var mainActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun whenStarts_progressNotShowing() {
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
    }

    @Test
    fun whenEdits_progressShowing() {
        onView(withId(R.id.progress)).check(matches(not(isDisplayed())))
        onView(withId(android.support.design.R.id.search_button)).perform(click())
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText(TEXT));
        onView(withId(R.id.progress)).check(matches((isDisplayed())))
    }

}
