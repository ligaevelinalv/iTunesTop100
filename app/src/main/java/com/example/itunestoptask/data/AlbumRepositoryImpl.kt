package com.example.itunestoptask.data

import android.util.Log
import com.example.itunestoptask.domain.Album
import com.example.itunestoptask.domain.AlbumRepository
import com.example.itunestoptask.network.ITunesApiService
import com.example.itunestoptask.network.NetworkResult
import javax.inject.Inject

data class AlbumRepositoryImpl
@Inject
constructor(
    private val apiService: ITunesApiService,
) : AlbumRepository {
    override suspend fun getTopAlbums(): NetworkResult<List<Album>> =
        try {
            val response = apiService.getTopAlbums()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body.toAlbumList())
            } else {
                Log.e("AlbumRepositoryImpl", "An API error has occurred.")
                NetworkResult.Error()
            }
        } catch (e: Exception) {
            Log.e("AlbumRepositoryImpl", "An Exception was caught: ${e.message}")
            NetworkResult.Error()
        }
}
