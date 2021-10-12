package com.muramsyah.gitsid.movieapp.data

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.movieapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.movieapp.data.source.remote.response.DetailResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailInteractor @Inject constructor(private val remoteDataSource: RemoteDataSource) {

    interface OnFinishedListener {
        fun onGetMovieDetail(movie: LiveData<DetailResponse>)
        fun onFailure(error: String)
    }

    fun requestDataMovieDetail(onFinishedListener: OnFinishedListener, id: Int) {
        onFinishedListener.onGetMovieDetail(remoteDataSource.getDetailMovie(id))
    }

}