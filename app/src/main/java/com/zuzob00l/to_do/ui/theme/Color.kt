package com.zuzob00l.to_do.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF412A81)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

//priority colors:
val HighPriorityColor = Color(0xFFEB213F)
val MediumPriorityColor = Color(0xFFD69A59)
val LowPriorityColor = Color(0xFF7AA971)

//Dark Theme:
val Purple80 = Color(0xFFD0BCFF)
val LightGray = Color(0xFF8993AB)
val BackgroundColor = Color(0xFF454894)

val Colors.topAppBarColor: Color
    @Composable
    get() = if(isLight) Color.White else LightGray

val Colors.topAppBarBackground: Color
    @Composable
    get() = if(isLight) BackgroundColor else Color.Black

val Colors.fabBackgroundColor: Color
    @Composable
    get() = if(isLight) BackgroundColor else Purple80

val Colors.ListItemColor: Color
  @Composable
  get() = if(isLight) Purple80 else BackgroundColor

val Colors.ListItemTextColor: Color
   @Composable
   get() = if(isLight) Color.Black else Color.White
