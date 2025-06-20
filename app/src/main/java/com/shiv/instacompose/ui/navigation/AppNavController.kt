package com.shiv.instacompose.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

object Destinations {
    object Route {
        const val App = "app"
    }
}

@Composable
fun rememberAppNavController(navController: NavHostController = rememberNavController()): AppNavController =
    remember(navController) {
        AppNavController(navController)
    }

@Stable
class AppNavController(val navController: NavHostController) {
    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToRoute(route: String) {
        navController.navigate(route)
    }
}