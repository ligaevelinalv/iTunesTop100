package com.example.itunestoptask.network

import com.example.itunestoptask.network.response.AlbumsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ITunesApiService {
    @GET("/us/rss/topalbums/limit=100/json")
    suspend fun getTopAlbums(): Response<AlbumsResponse>
}
