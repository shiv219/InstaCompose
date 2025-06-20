package com.shiv.instacompose.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shiv.instacompose.ui.navigation.route.AppConstant
import com.shiv.instacompose.ui.navigation.route.AppRoute
import com.shiv.instacompose.ui.screen.profile.UserProfileScreen
import com.shiv.instacompose.ui.screen.story.StoryPlayer


fun NavGraphBuilder.addMainGraph(navController: NavController, navigateTo:(String)->Unit, navigateUp: () -> Unit){
    composable(AppRoute.PROFILE.route){ from->
        UserProfileScreen(navigateTo, navigateUp)
    }
    composable(AppRoute.STORY.route){ from->
        val bookId = from.arguments?.getString(AppConstant.INDEX)
        StoryPlayer(bookId?:"0", navigateTo, navigateUp)
    }
}