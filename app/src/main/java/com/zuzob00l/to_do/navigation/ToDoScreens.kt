package com.zuzob00l.to_do.navigation

import java.lang.IllegalArgumentException

enum class ToDoScreens
{
    StartScreen,
    HomeScreen();

    companion object {
        fun fromRoute(route: String?): ToDoScreens
        = when(route?.substringBefore("/")) {
            StartScreen.name -> StartScreen
            HomeScreen.name -> HomeScreen
            null -> StartScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}
