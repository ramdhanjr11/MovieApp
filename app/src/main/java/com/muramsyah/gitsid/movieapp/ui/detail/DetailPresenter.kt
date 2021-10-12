package com.muramsyah.gitsid.movieapp.ui.detail

import androidx.lifecycle.LiveData
import com.muramsyah.gitsid.movieapp.data.DetailInteractor
import com.muramsyah.gitsid.movieapp.data.source.remote.response.DetailResponse

class DetailPresenter(private val detailView: DetailView, private val detailInteractor: DetailInteractor) : DetailInteractor.OnFinishedListener{

    fun setMovieId(id: Int) {
        detailView.onLoading(true)
        detailInteractor.requestDataMovieDetail(this, id)
    }

    override fun onGetMovieDetail(movie: LiveData<DetailResponse>) {
        detailView.onLoading(false)
        detailView.onSuccess(movie)
    }

    override fun onFailure(error: String) {
        detailView.onLoading(false)
        detailView.onError(error)
    }
}