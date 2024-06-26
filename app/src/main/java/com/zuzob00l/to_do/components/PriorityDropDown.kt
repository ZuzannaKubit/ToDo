package com.zuzob00l.to_do.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zuzob00l.to_do.R
import com.zuzob00l.to_do.data.models.Priority
import com.zuzob00l.to_do.ui.theme.ListItemTextColor
import com.zuzob00l.to_do.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit)
{
    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(
        targetValue = if(expanded) 180f else 0f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable { expanded = true }
            .border(
                shape = MaterialTheme.shapes.small,
                width = 1.dp,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled)
            ),
        verticalAlignment = Alignment.CenterVertically)
    {
        Canvas(
            modifier = Modifier
                .padding(start = 20.dp)
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f))
        {
            drawCircle(color = priority.color)
        }

        Text(
            modifier = Modifier
                .padding(start = 10.dp)
                .weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.ListItemTextColor)

       IconButton(
           modifier = Modifier
               .alpha(ContentAlpha.medium)
               .rotate(degrees = angle),
           onClick = { expanded = true })
       {
           Icon(
               modifier = Modifier.weight(1.5f),
               imageVector = Icons.Filled.ArrowDropDown,
               contentDescription = stringResource(R.string.arrow_drop_down))
       }

        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(fraction = 0.90f),
            expanded = expanded,
            onDismissRequest = { expanded = false })
        {
            DropdownMenuItem(
                onClick = { expanded = false
                onPrioritySelected(Priority.LOW) })
            {
                PriorityItem(priority = Priority.LOW)
            }

            DropdownMenuItem(
                onClick = { expanded = false
                    onPrioritySelected(Priority.MEDIUM) })
            {
                PriorityItem(priority = Priority.MEDIUM)
            }

            DropdownMenuItem(
                onClick = { expanded = false
                    onPrioritySelected(Priority.HIGH) })
            {
                PriorityItem(priority = Priority.HIGH)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PriorityDropDownPreview()
{
    PriorityDropDown(
        priority = Priority.LOW,
        onPrioritySelected = {})
}