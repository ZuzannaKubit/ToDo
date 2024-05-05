package com.zuzob00l.to_do.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.zuzob00l.to_do.R
import com.zuzob00l.to_do.ui.theme.fabBackgroundColor
import com.zuzob00l.to_do.util.Actions
import com.zuzob00l.to_do.util.SearchAppBarState
import com.zuzob00l.to_do.viewModels.SharedViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel)
{
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTask()
        sharedViewModel.readSortState()
    }

    val action by sharedViewModel.action

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    //collect as state flow our tasks from database:
    val allTasks by sharedViewModel.allTasks.collectAsState()
    //when searchBar is triggered:
    val searchedTasks by sharedViewModel.searchTask.collectAsState()

    //val sortState by sharedViewModel.sortState.value

    val scaffoldState = rememberScaffoldState()


    DisplaySnackBar(
        scaffoldState = scaffoldState,
        handleDatabaseActions = { sharedViewModel.handleDatabaseAction(action) },
        taskTitle = sharedViewModel.title.value,
        action = action,
        onUndoClicked = { sharedViewModel.action.value = it })

 Scaffold(
     scaffoldState = scaffoldState,
     topBar = { ListAppBar(
         sharedViewModel = sharedViewModel,
         searchAppBarState = searchAppBarState,
         searchTextState = searchTextState)},
     content = { paddingValues ->
         ListContent(
             modifier = Modifier.padding(paddingValues),
             allTasks = allTasks,
             searchedTasks = searchedTasks,
             searchAppBarState = searchAppBarState,
             navigateToTaskScreen = navigateToTaskScreen)
               },
     floatingActionButton = { ListFab(navigateToTaskScreen) })

}

@Composable
fun ListFab(onFabClicked: (taskId: Int) -> Unit)
{
    FloatingActionButton(
        onClick = { onFabClicked(-1) },
        backgroundColor = MaterialTheme.colors.fabBackgroundColor)
    {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.add_button),
            tint = Color.White)
    }
}

@Composable
fun DisplaySnackBar(
    scaffoldState: ScaffoldState,
    handleDatabaseActions:() -> Unit,
    onUndoClicked: (Actions) -> Unit,
    taskTitle: String,
    action: Actions)
{
   handleDatabaseActions()

    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = action) {
        if(action != Actions.NO_ACTION)
        {
            scope.launch {
                val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
                    message = setMessage(action, taskTitle = taskTitle),
                    actionLabel = setActionLabel(action)
                )
                //if UNDO was clicked:
                undoDeletedTask(
                    action = action,
                    snackBarResult = snackBarResult,
                    onUndoClicked = onUndoClicked
                )
            }
        }
        }
    }
private fun setMessage(action: Actions, taskTitle: String): String
{
    return when(action) {
        Actions.DELETE_ALL -> "All tasks removed"
        else -> "${action.name}: $taskTitle"
    }
}

//function to show Undo option on a SnackBar:
private fun setActionLabel(action: Actions): String
{
    return if(action.name == "DELETE") "UNDO"
           else "OK"
}

private fun undoDeletedTask(
    action: Actions,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Actions) -> Unit)
{
    if(snackBarResult == SnackbarResult.ActionPerformed && action == Actions.DELETE)
        onUndoClicked(Actions.UNDO)
}

/*
@Preview(showBackground = true)
@Composable
private fun ListScreenPreview()
{
    ListScreen(
        navigateToTaskScreen = {})
}
*/