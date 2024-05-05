package com.zuzob00l.to_do.screens.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zuzob00l.to_do.R

//composable to display when the database is empty
@Composable
fun EmptyContent()
{
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Text(
            text = stringResource(R.string.empty_notes_list),
            color = Color.Gray,
            fontSize = androidx.compose.material.MaterialTheme.typography.h6.fontSize)
    }
}

@Composable
@Preview(showBackground = true)
fun EmptyContentPreview() {
    EmptyContent()
}