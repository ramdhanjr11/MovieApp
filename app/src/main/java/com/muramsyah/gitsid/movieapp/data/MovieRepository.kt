package com.muramsyah.gitsid.movieapp.data

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.movieapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val remoteDataSource: RemoteDataSource) : MovieDataSource {

    override fun getMovieLatest(): LiveData<List<ResultsItem>> {
        return remoteDataSource.getMovieLatest()
    }

    override fun getMoviePopular(): LiveData<List<ResultsItem>> {
        return remoteDataSource.getMoviePopular()
    }
}