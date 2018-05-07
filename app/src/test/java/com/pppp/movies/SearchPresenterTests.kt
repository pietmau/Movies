package com.pppp.movies

import android.arch.paging.PagedList
import com.jakewharton.rxrelay2.BehaviorRelay
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pppp.movies.apis.search.Movie
import com.pppp.movies.search.viewmodel.PagedListFactory
import com.pppp.movies.search.viewmodel.SearchPresenter
import com.pppp.movies.search.viewmodel.SearchView
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner



@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTests {
    @Mock lateinit var pagedListFactory: PagedListFactory
    @Mock lateinit var pagedList: PagedList<Movie>
    @Mock lateinit var view: SearchView
    lateinit var pagedListFlowable: Flowable<PagedList<Movie>>
    lateinit var subject: BehaviorRelay<PagedList<Movie>>
    lateinit var presenter: SearchPresenter

    @Before
    fun setUp() {
        subject = BehaviorRelay.create<PagedList<Movie>>()
        pagedListFlowable = Flowable.just(pagedList)
        whenever(pagedListFactory.getPagesList(anyString(), anyInt())).thenReturn(pagedListFlowable)
        presenter = SearchPresenter(subject, pagedListFactory)
        presenter.subscribe(view)
    }

    @Test
    fun when_nonEmptyQuery_thenSearchResultsAreSetInView() {
        // WHEN
        presenter.onQueryTextChange(NOT_EMPTY_QUERY)
        // THEN
        verify(view).onMovieAvailable(pagedList)
    }

    @Test
    fun when_nullQuery_thenSearchResultsAreNotSetInView() {
        // WHEN
        presenter.onQueryTextChange(null)
        // THEN
        verify(view, never()).onMovieAvailable(pagedList)
    }

    @Test
    fun when_emptyQuery_thenSearchResultsAreNotSetInView() {
        // WHEN
        presenter.onQueryTextChange("")
        // THEN
        verify(view, never()).onMovieAvailable(pagedList)
    }

    @Test
    fun when_queryProducesAnError_thenSearchResultsAreNotSetInView() {
        // GIVEN
        requestProducesException()
        // WHEN
        presenter.onQueryTextChange(NOT_EMPTY_QUERY)
        // THEN
        verify(view, never()).onMovieAvailable(pagedList)
    }

    @Test
    fun when_queryProducesAnError_thenViewIsNotifiedOfError() {
        // GIVEN
        val exception = requestProducesException()
        // WHEN
        presenter.onQueryTextChange(NOT_EMPTY_QUERY)
        // THEN
        verify(view).onError(exception)
    }

    @Test
    fun when_nonEmptyQuery_thenStopsLoading() {
        // WHEN
        presenter.onQueryTextChange(NOT_EMPTY_QUERY)
        // THEN
        val inOrder = inOrder(view)
        inOrder.verify(view).showProgress(true)
        inOrder.verify(view).showProgress(false)
    }

    @Test
    fun when_nullQuery_thenStopsLoading() {
        // WHEN
        presenter.onQueryTextChange(null)
        // THEN
        verify(view, never()).showProgress(anyBoolean())
    }

    @Test
    fun when_emptyQuery_thenStopsLoading() {
        // WHEN
        presenter.onQueryTextChange("")
        // THEN
        verify(view, never()).showProgress(anyBoolean())
    }

    @Test
    fun when_click_thenStartsDetail() {
        // WHEN
        val movie = mock(Movie::class.java)
        presenter.onMovieSelected(movie)
        // THEN
        verify(view).startDetailScreen(movie)
    }

    private fun requestProducesException(): Exception {
        val exception = Exception()
        pagedListFlowable = Flowable.error(exception)
        whenever(pagedListFactory.getPagesList(anyString(), anyInt())).thenReturn(pagedListFlowable)
        presenter = SearchPresenter(subject, pagedListFactory)
        presenter.subscribe(view)
        return exception
    }

    companion object {
        private const val NOT_EMPTY_QUERY = "not_empty"
    }
}