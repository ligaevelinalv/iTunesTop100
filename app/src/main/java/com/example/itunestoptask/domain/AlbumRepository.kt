package com.example.itunestoptask.domain

import com.example.itunestoptask.network.NetworkResult

interface AlbumRepository {
    suspend fun getTopAlbums(): NetworkResult<List<Album>>
}
