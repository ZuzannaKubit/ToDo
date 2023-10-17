package com.zuzob00l.to_do.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zuzob00l.to_do.navigation.destinations.listComposable
import com.zuzob00l.to_do.navigation.destinations.taskComposable
import com.zuzob00l.to_do.util.Constants.LIST_SCREEN
import com.zuzob00l.to_do.viewModels.SharedViewModel
@ExperimentalMaterialApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel)
{
    val screens = remember(navController) { Screens(navController) }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN)
    {
       listComposable(
           navigateToTaskScreen = screens.task,
           sharedViewModel = sharedViewModel)

        taskComposable(
            navigateToListScreen = screens.list)
    }
}