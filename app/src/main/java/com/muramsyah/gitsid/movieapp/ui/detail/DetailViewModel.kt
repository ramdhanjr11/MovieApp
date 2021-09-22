package com.muramsyah.gitsid.movieapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.muramsyah.gitsid.movieapp.data.MovieRepository
import com.muramsyah.gitsid.movieapp.data.source.remote.response.DetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(val movieRepository: MovieRepository) : ViewModel() {

    fun getDetailMovie(movieId: Int): LiveData<DetailResponse> = movieRepository.getDetailMovie(movieId)

}