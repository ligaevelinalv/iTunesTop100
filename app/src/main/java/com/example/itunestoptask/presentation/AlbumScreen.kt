package com.example.itunestoptask.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.itunestoptask.presentation.components.AlbumListItem
import com.example.itunestoptask.presentation.components.EmptyListState
import com.example.itunestoptask.presentation.components.ErrorDialog
import com.example.itunestoptask.presentation.components.ListTitle
import com.example.itunestoptask.theme.darkOlive
import com.example.itunestoptask.theme.progressColor
import com.example.itunestoptask.utils.testAlbumList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumScreen(
    viewModel: AlbumViewModel,
    onItemClicked: (String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        ErrorDialog(
            onOkClicked = {
                showDialog = false
                viewModel.loadImages()
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.uiActions.collect {
            when (it) {
                is AlbumViewModel.UiAction.Error -> {
                    showDialog = true
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = darkOlive
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        when (uiState.albumState) {
            AlbumViewModel.AlbumState.LOADING, AlbumViewModel.AlbumState.ERROR -> {
                Column(
                    modifier = modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(color = progressColor)
                }
            }

            AlbumViewModel.AlbumState.EMPTY -> {
                EmptyListState(modifier = modifier)
            }

            AlbumViewModel.AlbumState.FINISHED -> {
                AlbumScreenContent(
                    modifier = modifier,
                    uiState = uiState,
                    onItemClick = onItemClicked
                )
            }
        }
    }
}

@Composable
private fun AlbumScreenContent(
    modifier: Modifier = Modifier,
    uiState: AlbumViewModel.AlbumUiState,
    onItemClick: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    ) {
        item {
            ListTitle()
        }
        items(
            items = uiState.albumList,
            key = { item -> item.id }
        ) { album ->
            AlbumListItem(
                album = album,
                onClick = {
                    onItemClick(album.id)
                }
            )
            if (album != uiState.albumList.last()) {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Preview()
@Composable
private fun AlbumScreenPreview() {
    AlbumScreenContent(
        uiState = AlbumViewModel.AlbumUiState(albumList = testAlbumList)
    )
}
