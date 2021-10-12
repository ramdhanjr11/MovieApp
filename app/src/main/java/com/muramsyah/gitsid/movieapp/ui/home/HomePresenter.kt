package com.muramsyah.gitsid.movieapp.ui.home

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.movieapp.data.MovieInteractor
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class HomePresenter(private val homeView: HomeView, private val movieInteractor: MovieInteractor) : MovieInteractor.OnFinishedListener {

    init {
        homeView.onLoading(true)
        movieInteractor.requestDataMoviePopular(this@HomePresenter)
        movieInteractor.requestDataMovieLatest(this@HomePresenter)
    }

    override fun onGetMoviePopularSuccess(movies: LiveData<List<ResultsItem>>) {
        homeView.onLoading(false)
        homeView.onMoviePopularSuccess(movies)
    }

    override fun onGetMovieLatestSuccess(movies: LiveData<List<ResultsItem>>) {
        homeView.onLoading(false)
        homeView.onMovieLatestSuccess(movies)
    }

    override fun onFailure(error: String) {
        homeView.onError(error)
    }

}