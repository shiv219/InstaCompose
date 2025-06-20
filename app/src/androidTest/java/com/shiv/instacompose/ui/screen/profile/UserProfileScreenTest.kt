package com.shiv.instacompose.ui.screen.profile

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.shiv.instacompose.ui.InstaComposeApp
import com.shiv.instacompose.ui.navigation.rememberAppNavController
import com.shiv.instacompose.ui.navigation.route.AppRoute
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import test.activity.HiltTestActivity

@HiltAndroidTest
class UserProfileScreenTest {

    @get:Rule(order = 0)
    val hilt = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<HiltTestActivity>()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hilt.inject()
        navController = TestNavHostController(composeRule.activity)
        navController.navigatorProvider.addNavigator(ComposeNavigator())
        composeRule.setContent {
            InstaComposeApp(rememberAppNavController(navController))
        }
    }

    @Test
    fun userProfileScreen_rendersHeaderAndTabs() {
        composeRule.onNodeWithText("shiv_yadav").assertExists()
        composeRule.onNodeWithContentDescription("back Icon").assertExists()
        composeRule.onNodeWithText("Following").assertExists()
        composeRule.onNodeWithText("Message").assertExists()
    }

    @Test
    fun navigateToStoryPlayerScreen_onStoryClick() {
        composeRule.onAllNodesWithContentDescription("Story Highlight").onFirst().performClick()
        assert(navController.currentBackStackEntry?.destination?.route?.startsWith(AppRoute.STORY.route) == true)
    }

    @After
    fun tearDown() {

    }
}