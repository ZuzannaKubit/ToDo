package com.zuzob00l.to_do.navigation.destinations

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.zuzob00l.to_do.screens.list.ListScreen
import com.zuzob00l.to_do.util.Constants.LIST_ARGUMENT_KEY
import com.zuzob00l.to_do.util.Constants.LIST_SCREEN
import com.zuzob00l.to_do.util.toAction
import com.zuzob00l.to_do.viewModels.SharedViewModel
@ExperimentalMaterialApi
fun NavGraphBuilder.listComposable(
    navigateToTaskScreen:(Int) -> Unit,
    sharedViewModel: SharedViewModel)
{
    composable(
        route = LIST_SCREEN,
        arguments = listOf(navArgument(LIST_ARGUMENT_KEY) { type = NavType.StringType })
    ) {
        navBackStackEntry ->
        val action = navBackStackEntry.arguments?.getString(LIST_ARGUMENT_KEY).toAction()

        LaunchedEffect(key1 = action) {
            sharedViewModel.action.value = action
        }

          ListScreen(
              navigateToTaskScreen = navigateToTaskScreen,
              sharedViewModel = sharedViewModel)
    }
}