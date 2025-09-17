package com.example.itunestoptask.di

import com.example.itunestoptask.data.AlbumRepositoryImpl
import com.example.itunestoptask.domain.AlbumRepository
import com.example.itunestoptask.network.ITunesApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideConverterRepository(apiService: ITunesApiService): AlbumRepository =
        AlbumRepositoryImpl(apiService)
}
