package com.zuzob00l.to_do.navigation

import androidx.navigation.NavController
import com.zuzob00l.to_do.util.Actions
import com.zuzob00l.to_do.util.Constants.LIST_SCREEN

class Screens(navController: NavController)
{
    val list: (Actions) -> Unit = { action ->
        navController.navigate("list/${action.name}") {
            popUpTo(LIST_SCREEN){ inclusive = true }
        }
    }

    val task: (Int) -> Unit = {  taskId ->
        navController.navigate("task/$taskId")
    }
}