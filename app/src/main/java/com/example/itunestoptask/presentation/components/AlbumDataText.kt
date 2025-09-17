package com.example.itunestoptask.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.itunestoptask.R
import com.example.itunestoptask.theme.detailsDescription
import com.example.itunestoptask.theme.detailsLabel
import com.example.itunestoptask.utils.testAlbumList

@Composable
fun AlbumDataText(
    stringId: Int,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(stringId),
            style = detailsLabel
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description,
            style = detailsDescription,
            textAlign = TextAlign.End
        )
    }
}

@Preview
@Composable
private fun AlbumDataPreview() {
    AlbumDataText(
        stringId = R.string.artist_text,
        description = testAlbumList.last().artistName
    )
}
