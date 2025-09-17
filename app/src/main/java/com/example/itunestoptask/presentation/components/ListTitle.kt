package com.example.itunestoptask.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.itunestoptask.R
import com.example.itunestoptask.theme.largeTitle
import com.example.itunestoptask.theme.paleYellow

@Composable
fun ListTitle() {
    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = stringResource(R.string.list_title),
        style = largeTitle,
        color = paleYellow
    )
}
