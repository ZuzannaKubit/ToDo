package com.zuzob00l.to_do.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zuzob00l.to_do.data.models.Priority
import com.zuzob00l.to_do.ui.theme.ListItemTextColor
import com.zuzob00l.to_do.ui.theme.SMALL_PADDING
import com.zuzob00l.to_do.ui.theme.Typography

@Composable
fun PriorityItem(priority: Priority)
{
    Row(verticalAlignment = Alignment.CenterVertically)
    {
        Canvas(
            modifier = Modifier.size(16.dp)) {
            drawCircle(color = priority.color)
        }
       Text(
           modifier = Modifier.padding(start = SMALL_PADDING),
           text = priority.name,
           style = Typography.titleSmall,
           color = androidx.compose.material.MaterialTheme.colors.ListItemTextColor)
    }
}
@Composable
@Preview()
fun PriorityItemPreview()
{
    PriorityItem(Priority.LOW)
}