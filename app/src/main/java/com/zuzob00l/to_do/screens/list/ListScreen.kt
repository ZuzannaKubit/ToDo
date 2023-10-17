package com.zuzob00l.to_do.screens.list

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zuzob00l.to_do.R
import com.zuzob00l.to_do.ui.theme.fabBackgroundColor
import com.zuzob00l.to_do.util.SearchAppBarState
import com.zuzob00l.to_do.viewModels.SharedViewModel
@ExperimentalMaterialApi
@Composable
fun ListScreen(
    navigateToTaskScreen: (Int) -> Unit,
    sharedViewModel: SharedViewModel)
{
    sharedViewModel.getAllTask()

    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState: String by sharedViewModel.searchTextState
    //collect as state flow our tasks from database:
    val allTasks by sharedViewModel.allTasks.collectAsState()


 Scaffold(
     topBar = { ListAppBar(
         sharedViewModel = sharedViewModel,
         searchAppBarState = searchAppBarState,
         searchTextState = searchTextState)},
     content = { paddingValues ->
         ListContent(
             modifier = Modifier.padding(paddingValues),
             tasks = allTasks,
             navigateToTaskScreen = navigateToTaskScreen) },
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
/*
@Preview(showBackground = true)
@Composable
private fun ListScreenPreview()
{
    ListScreen(
        navigateToTaskScreen = {})
}
*/