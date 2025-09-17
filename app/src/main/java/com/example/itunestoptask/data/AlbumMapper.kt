package com.example.itunestoptask.data

import com.example.itunestoptask.domain.Album
import com.example.itunestoptask.network.response.AlbumsResponse

fun AlbumsResponse.toAlbumList() =
    this.feed.entry.map {
        Album(
            id = it.albumId.attributes.id,
            albumName = it.albumName.label,
            artistName = it.artistName.label,
            smallImageUrl = it.imageUrl.firstOrNull()?.label,
            largeImageUrl = it.imageUrl.lastOrNull()?.label,
            genre = it.category.attributes.label,
            releaseDate = it.releaseDate.attributes.label
        )
    }
