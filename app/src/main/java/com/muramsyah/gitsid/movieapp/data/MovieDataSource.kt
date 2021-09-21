package com.muramsyah.gitsid.movieapp.data

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem

interface MovieDataSource {

    fun getMovieLatest(): LiveData<List<ResultsItem>>

    fun getMoviePopular(): LiveData<List<ResultsItem>>

}