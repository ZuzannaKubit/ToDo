package com.zuzob00l.to_do.navigation.destinations

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zuzob00l.to_do.screens.task.TaskScreen
import com.zuzob00l.to_do.util.Actions
import com.zuzob00l.to_do.util.Constants.TASK_ARGUMENT_KEY
import com.zuzob00l.to_do.util.Constants.TASK_SCREEN
import com.zuzob00l.to_do.viewModels.SharedViewModel

fun NavGraphBuilder.taskComposable(
    navigateToListScreen: (Actions) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = TASK_SCREEN,
        arguments = listOf(navArgument(TASK_ARGUMENT_KEY) {
            type = NavType.IntType
        })
    ){
      navBackStackEntry ->
        val taskId = navBackStackEntry.arguments!!.getInt(TASK_ARGUMENT_KEY)
        sharedViewModel.getSelectedTask(taskId)
        val selectedTask by sharedViewModel.selectedTask.collectAsState()

        LaunchedEffect(key1 = selectedTask) {
            //when we clicked UNDO on a snackBar te function update task set ours
            // toDoTask values like title, desc, priority to null and we don t want this to happen,
            //because we want to receive this values and undo deleted task

            //we deleted task from our database but not reset task values in view Model
            //so we can undo task with values from view model

            if(selectedTask != null || taskId == -1)
            {
                sharedViewModel.updateTaskField(selectedTask)
            }
            sharedViewModel.updateTaskField(selectedTask)
        }

        TaskScreen(
            sharedViewModel = sharedViewModel,
            navigateToListScreen = navigateToListScreen,
            selectedTask = selectedTask)
    }
}