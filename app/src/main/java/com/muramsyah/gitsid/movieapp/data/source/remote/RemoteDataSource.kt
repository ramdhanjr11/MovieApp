package com.muramsyah.gitsid.movieapp.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.muramsyah.gitsid.movieapp.BuildConfig
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ApiService
import com.muramsyah.gitsid.movieapp.data.source.remote.response.DetailResponse
import com.muramsyah.gitsid.movieapp.data.source.remote.response.MovieResponse
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    companion object {
        private val TAG = RemoteDataSource::class.java.simpleName
    }

    fun getMovieLatest(): LiveData<List<ResultsItem>> {
        val results = MutableLiveData<List<ResultsItem>>()

        apiService.getMovieLatest(BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val resultsResponse = ArrayList<ResultsItem>()
                        val data = response.body()

                        data?.results?.forEach {
                            resultsResponse.add(it!!)
                        }

                        results.postValue(resultsResponse)
                    } else {
                        Log.d(TAG, "onResponse: Gagal Mengambil data")
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })

        return results
    }

    fun getMoviePopular(): LiveData<List<ResultsItem>> {
        val results = MutableLiveData<List<ResultsItem>>()

        apiService.getMoviePopular(BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        val resultsResponse = ArrayList<ResultsItem>()
                        val data = response.body()

                        data?.results?.forEach {
                            resultsResponse.add(it!!)
                        }

                        results.postValue(resultsResponse)
                    } else {
                        Log.d(TAG, "onResponse: Gagal Mengambil data")
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })

        return results
    }

    fun getDetailMovie(movieId: Int): LiveData<DetailResponse> {
        val results = MutableLiveData<DetailResponse>()

        apiService.getDetailMovie(movieId, BuildConfig.TMDB_API_KEY)
            .enqueue(object : Callback<DetailResponse> {
                override fun onResponse(
                    call: Call<DetailResponse>,
                    response: Response<DetailResponse>
                ) {
                    if (response.isSuccessful) {
                        results.postValue(response.body())
                    } else {
                        Log.d(TAG, "onResponse: Gagal mengambil data")
                    }
                }

                override fun onFailure(call: Call<DetailResponse>, t: Throwable) {
                    Log.d(TAG, "onFailure: ${t.message}")
                }

            })

        return results
    }

}