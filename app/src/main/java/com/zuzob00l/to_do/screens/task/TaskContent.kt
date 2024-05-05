package com.zuzob00l.to_do.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zuzob00l.to_do.R
import com.zuzob00l.to_do.components.PriorityDropDown
import com.zuzob00l.to_do.data.models.Priority
import com.zuzob00l.to_do.data.models.ToDoTask
import com.zuzob00l.to_do.ui.theme.ListItemColor
import com.zuzob00l.to_do.ui.theme.ListItemTextColor

@Composable
fun TaskContent(
    modifier: Modifier = Modifier,
    title: String,
    onTitleChange: (String) -> Unit,
    description: String,
    onDescriptionChange: (String) -> Unit,
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(10.dp))
    {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 5.dp),
            value = title,
            shape = RoundedCornerShape(4.dp),
            singleLine = true,
            textStyle = MaterialTheme.typography.body1,
            label = { Text(text = stringResource(R.string.title)) },
            onValueChange = onTitleChange)

        PriorityDropDown(
            priority = priority,
            onPrioritySelected = onPrioritySelected)

        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 20.dp),
            shape = RoundedCornerShape(7),
            backgroundColor = MaterialTheme.colors.ListItemColor)
        {
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10),
                value = description,
                onValueChange = onDescriptionChange,
                label = { Text(text = stringResource(R.string.description)) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    textColor = MaterialTheme.colors.ListItemTextColor
                ),
                textStyle = MaterialTheme.typography.body1,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TaskContentPreview()
{
    TaskContent(
        title = "title",
        onTitleChange = { },
        description = "description",
        onDescriptionChange = {},
        priority = Priority.LOW,
        onPrioritySelected = {}
    )
}