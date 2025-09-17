package com.example.itunestoptask.network.response

import com.google.gson.annotations.SerializedName

data class EntryResponse(
    @SerializedName("im:name")
    val albumName: NameResponse,
    @SerializedName("im:artist")
    val artistName: ArtistResponse,
    @SerializedName("im:image")
    val imageUrl: List<ImageUrlResponse>,
    @SerializedName("category")
    val category: CategoryResponse,
    @SerializedName("im:releaseDate")
    val releaseDate: ReleaseResponse,
    @SerializedName("id")
    val albumId: AlbumIdResponse
)
