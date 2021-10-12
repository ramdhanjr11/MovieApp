package com.muramsyah.gitsid.movieapp.data

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.movieapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieInteractor @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    interface OnFinishedListener {
        fun onGetMoviePopularSuccess(movies: LiveData<List<ResultsItem>>)
        fun onGetMovieLatestSuccess(movies: LiveData<List<ResultsItem>>)
        fun onFailure(error: String)
    }

    fun requestDataMoviePopular(onFinishedListener: OnFinishedListener) {
        onFinishedListener.onGetMoviePopularSuccess(remoteDataSource.getMoviePopular())
    }

    fun requestDataMovieLatest(onFinishedListener: OnFinishedListener) {
        onFinishedListener.onGetMovieLatestSuccess(remoteDataSource.getMovieLatest())
    }


}