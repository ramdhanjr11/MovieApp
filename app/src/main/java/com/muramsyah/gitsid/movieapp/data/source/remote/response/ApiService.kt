package com.muramsyah.gitsid.movieapp.data.source.remote.response

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    fun getMovieLatest(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>
}