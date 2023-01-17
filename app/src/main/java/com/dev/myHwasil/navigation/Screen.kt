package com.dev.myHwasil.navigation

sealed class Screen (val route : String) {
    object Splash : Screen("splash_screen");
    object Home : Screen("home_screen")
    object Map : Screen("map_screen")
}