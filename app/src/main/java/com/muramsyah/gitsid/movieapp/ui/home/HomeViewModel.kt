package com.muramsyah.gitsid.movieapp.ui.home

import androidx.lifecycle.ViewModel
import com.muramsyah.gitsid.movieapp.data.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieRepository: MovieRepository): ViewModel() {

    val movieLatest = movieRepository.getMovieLatest()
    val moviePopular = movieRepository.getMoviePopular()

}