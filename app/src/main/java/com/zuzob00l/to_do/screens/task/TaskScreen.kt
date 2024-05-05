package com.zuzob00l.to_do.screens.task

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.zuzob00l.to_do.data.models.Priority
import com.zuzob00l.to_do.data.models.ToDoTask
import com.zuzob00l.to_do.util.Actions
import com.zuzob00l.to_do.viewModels.SharedViewModel

@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    selectedTask: ToDoTask?,
    navigateToListScreen: (Actions) -> Unit)
{
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = { TaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = { action ->
             if(action == Actions.NO_ACTION)
                 navigateToListScreen(action)
                else if(sharedViewModel.validateFields())
                    navigateToListScreen(action)
                else
                    displayToast(context)} )
                 },
        content = { paddingValues ->
            TaskContent(
            modifier = Modifier.padding(paddingValues = paddingValues),
            title = title,
            onTitleChange = {
                sharedViewModel.updateTitle(it)
                            },
            description = description,
            onDescriptionChange = {
                sharedViewModel.description.value = it
                                  },
            priority = priority,
            onPrioritySelected = { sharedViewModel.priority.value = it }
        ) })
}

fun displayToast(context: Context)
{
   Toast.makeText(
       context,
       "Empty Fields!",
       Toast.LENGTH_LONG)
}

