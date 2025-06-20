package com.shiv.instacompose.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.shiv.instacompose.ui.navigation.AppNavController
import com.shiv.instacompose.ui.navigation.Destinations
import com.shiv.instacompose.ui.navigation.addMainGraph
import com.shiv.instacompose.ui.navigation.rememberAppNavController
import com.shiv.instacompose.ui.navigation.route.AppRoute
import com.shiv.instacompose.ui.screen.profile.UserProfileScreen
import com.shiv.instacompose.ui.theme.InstaComposeTheme

@Composable
fun InstaComposeApp(appNavController:AppNavController = rememberAppNavController()) {
    InstaComposeTheme {

        NavHost(
            navController = appNavController.navController,
            startDestination = AppRoute.PROFILE.route
        ) {
            appNavGraph(
                upPress = appNavController::upPress,
                onNavigateToRoute = appNavController::navigateToRoute,
                appNavController
            )
        }
    }
}

fun NavGraphBuilder.appNavGraph(
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit,
    navController: AppNavController
) {
    navigation(
        route = Destinations.Route.App,
        startDestination = AppRoute.PROFILE.route
    ) {
        addMainGraph(
            navController = navController.navController,
            navigateTo = onNavigateToRoute,
            navigateUp = upPress
        )
    }
    composable(AppRoute.PROFILE.route) { from ->
        UserProfileScreen(onNavigateToRoute, upPress)
    }

}
