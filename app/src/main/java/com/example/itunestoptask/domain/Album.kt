package com.example.itunestoptask.domain

data class Album(
    val id: String,
    val albumName: String,
    val artistName: String,
    val smallImageUrl: String?,
    val largeImageUrl: String?,
    val genre: String,
    val releaseDate: String,
)
