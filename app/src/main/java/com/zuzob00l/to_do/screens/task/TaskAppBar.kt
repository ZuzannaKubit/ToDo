package com.zuzob00l.to_do.screens.task

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.zuzob00l.to_do.R
import com.zuzob00l.to_do.components.DisplayAlertDialog
import com.zuzob00l.to_do.data.models.ToDoTask
import com.zuzob00l.to_do.ui.theme.ListItemTextColor
import com.zuzob00l.to_do.ui.theme.topAppBarBackground
import com.zuzob00l.to_do.util.Actions

@Composable
fun TaskAppBar(
    modifier: Modifier = Modifier,
    selectedTask: ToDoTask?,
    navigateToListScreen: (Actions) -> Unit)
{
    if(selectedTask == null)
    //so was clicked floatingAction button
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    else
        ExistingTaskAppBar(
            selectedTask = selectedTask,
            navigateToListScreen = navigateToListScreen)
}

@Composable
fun NewTaskAppBar(
    navigateToListScreen: (Actions) -> Unit
)
{
   TopAppBar(
       title = { Text(
           text = stringResource(R.string.add_task),
           color = Color.White) },
       navigationIcon = { BackAction(onBackClicked = navigateToListScreen) },
       backgroundColor = androidx.compose.material.MaterialTheme.colors.topAppBarBackground,
       actions = {
           AddAction(onAddClicked = navigateToListScreen) })
}

@Composable
fun BackAction(
    onBackClicked: (Actions) -> Unit)
{
    IconButton(onClick = { onBackClicked(Actions.NO_ACTION) })
    {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_arrow_icon),
            tint = Color.White)
    }
}
@Composable
fun AddAction(
    onAddClicked: (Actions) -> Unit)
{
    IconButton(onClick = { onAddClicked(Actions.ADD) })
    {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.add_task),
            tint = Color.White)
    }
}

@Composable
fun ExistingTaskAppBar(
    selectedTask: ToDoTask,
    navigateToListScreen: (Actions) -> Unit)
{
  TopAppBar(
      navigationIcon = { CloseAction(onCloseClicked = navigateToListScreen)},
      title = { Text(
          text = selectedTask.title,
          color = Color.White,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis) },
      actions = { ExistingTaskAppBarActions(
          selectedTask = selectedTask,
          navigateToListScreen = navigateToListScreen) })
}

@Composable
fun ExistingTaskAppBarActions(
    selectedTask: ToDoTask,
    navigateToListScreen: (Actions) -> Unit)
{
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(R.string.delete_task, selectedTask.title),
        message = stringResource(R.string.delete_tasks_confirmation, selectedTask.title),
        openDialog = openDialog,
        closeDialog = { openDialog = false },
        onYesClicked = { navigateToListScreen(Actions.DELETE) })

    DeleteAction(onDeleteClicked = { openDialog = true })
    UpdateAction(onUpdateClicked = navigateToListScreen)
}
////////////////////////////////////////////////////////////////////////////////////////////////////
//actions for ExistingTaskAppBar:
@Composable
fun CloseAction(onCloseClicked: (Actions) -> Unit)
{
    IconButton(onClick = { onCloseClicked(Actions.NO_ACTION) })
    {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = stringResource(R.string.close_icon),
            tint = Color.White)
    }
}
@Composable
fun DeleteAction(onDeleteClicked: () -> Unit)
{
    IconButton(onClick = { onDeleteClicked() })
    {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(R.string.delete_icon),
            tint = Color.White)
    }
}
@Composable
fun UpdateAction(onUpdateClicked: (Actions) -> Unit)
{
    IconButton(onClick = { onUpdateClicked(Actions.UPDATE) })
    {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(R.string.update_icon),
            tint = Color.White)
    }
}