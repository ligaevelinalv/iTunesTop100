package com.example.itunestoptask.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itunestoptask.R
import com.example.itunestoptask.utils.testAlbumList

@Composable
fun AlbumData(
    artist: String,
    genre: String,
    releaseDate: String
) {
    Column {
        AlbumDataText(R.string.artist_text, artist)
        Spacer(modifier = Modifier.height(8.dp))
        AlbumDataText(R.string.genre_text, genre)
        Spacer(modifier = Modifier.height(8.dp))
        AlbumDataText(R.string.release_text, releaseDate)
    }
}

@Preview
@Composable
private fun AlbumDataPreview() {
    val testAlbum = testAlbumList.last()
    AlbumData(
        artist = testAlbum.artistName,
        genre = testAlbum.genre,
        releaseDate = testAlbum.releaseDate
    )
}
