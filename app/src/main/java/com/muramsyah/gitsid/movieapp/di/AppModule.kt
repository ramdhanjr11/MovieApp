package com.muramsyah.gitsid.movieapp.di

import com.muramsyah.gitsid.movieapp.data.source.remote.RemoteDataSource
import com.muramsyah.gitsid.movieapp.data.source.remote.response.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRepository(apiService: ApiService): RemoteDataSource = RemoteDataSource(apiService)

}