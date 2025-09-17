package com.example.itunestoptask.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itunestoptask.R
import com.example.itunestoptask.domain.Album
import com.example.itunestoptask.presentation.components.AlbumData
import com.example.itunestoptask.presentation.components.NetworkImage
import com.example.itunestoptask.theme.darkOlive
import com.example.itunestoptask.theme.detailsName
import com.example.itunestoptask.theme.largeTitle
import com.example.itunestoptask.theme.paleYellow
import com.example.itunestoptask.theme.topBarColor
import com.example.itunestoptask.utils.testAlbumList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    albumId: String,
    viewModel: AlbumViewModel,
    onNavigateBack: () -> Unit
) {
    val openedAlbum = viewModel.findAlbumFromId(albumId)
    val albumIndex = viewModel.getAlbumIndex(albumId)

    BackHandler {
        onNavigateBack()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = darkOlive,
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = "Arrow back button",
                            tint = paleYellow
                        )
                    }
                },
                colors =
                    topAppBarColors(
                        containerColor = topBarColor,
                        titleContentColor = paleYellow,
                    ),
                title = {
                    Text(
                        text = albumIndex.getTopBarText(),
                        style = largeTitle
                    )
                },
            )
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        DetailsScreenContent(
            modifier = modifier,
            album = openedAlbum
        )
    }
}

@Composable
private fun DetailsScreenContent(
    modifier: Modifier = Modifier,
    album: Album,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .clip(shape = RoundedCornerShape(12.dp))
            .background(color = paleYellow)
            .fillMaxWidth(),
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                NetworkImage(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(10.dp))
                        .size(170.dp)
                        .aspectRatio(1f), url = album.largeImageUrl
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = album.albumName,
                    style = detailsName,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))

                AlbumData(
                    artist = album.artistName,
                    genre = album.genre,
                    releaseDate = album.releaseDate
                )
            }
        }
    }
}

@Composable
private fun Int.getTopBarText() = "# ${this.plus(1)} ${stringResource(R.string.topbar_text)}"

@Preview(widthDp = 300)
@Composable
private fun DetailsScreenPreview() {
    DetailsScreenContent(
        album = testAlbumList.last()
    )
}
