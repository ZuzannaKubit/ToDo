package com.zuzob00l.to_do.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zuzob00l.to_do.navigation.destinations.listComposable
import com.zuzob00l.to_do.navigation.destinations.taskComposable
import com.zuzob00l.to_do.util.Constants.LIST_SCREEN

@Composable
fun SetupNavigation(navController: NavHostController)
{
    val screens = remember(navController) { Screens(navController) }

    NavHost(
        navController = navController,
        startDestination = LIST_SCREEN)
    {
       listComposable(navigateToTaskScreen = screens.task)
        taskComposable(navigateToListScreen = screens.list)
    }
}