package com.zuzob00l.to_do.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zuzob00l.to_do.screens.HomeScreen
import com.zuzob00l.to_do.screens.StartScreen

@Composable
fun ToDoNavigation()
{
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ToDoScreens.StartScreen.name)
    {
        //route to StartScreen:
        composable(ToDoScreens.StartScreen.name) {
            StartScreen(navController = navController)
        }

        //route to HomeScreen:
        composable(ToDoScreens.HomeScreen.name) {
            HomeScreen()
        }
    }
}