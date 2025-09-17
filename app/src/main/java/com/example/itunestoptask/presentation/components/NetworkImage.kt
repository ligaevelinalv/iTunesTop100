package com.example.itunestoptask.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.itunestoptask.R

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String?
) {
    val context = LocalContext.current
    val imageRequest = remember {
        ImageRequest.Builder(context)
            .data(url)
            .crossfade(true)
            .build()
    }
    AsyncImage(
        modifier = modifier,
        placeholder = painterResource(R.drawable.ic_image),
        error = painterResource(R.drawable.ic_broken_image),
        model = imageRequest,
        contentDescription = "Album art",
    )
}
