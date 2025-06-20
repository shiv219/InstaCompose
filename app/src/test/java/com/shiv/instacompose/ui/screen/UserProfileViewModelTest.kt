package com.shiv.instacompose.ui.screen

import androidx.paging.PagingData
import app.cash.turbine.test
import com.shiv.instacompose.core.DispatcherProvider
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import com.shiv.instacompose.rule.MainDispatcherRule
import com.shiv.instacompose.ui.screen.profile.UiState
import com.shiv.instacompose.ui.screen.profile.UserProfileViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class UserProfileViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private lateinit var viewModel: UserProfileViewModel
    private lateinit var useCase: UserProfileUseCase
    private lateinit var dispatcherProvider: DispatcherProvider
    val user = UserProfile(
        id = "1",
        name = "Shiv",
        userName = "shiv_yadav",
        profileThumbUrl = "thumb",
        profileImageUrl = "img",
        postsCount = "10",
        followerCount = "100",
        followingCount = "50",
        bio = "Android dev"
    )

    val storyList = listOf( UsersStory(
        storyId = "s1",
        userId = "u1",
        createdAt = 1717939834921,
        postThumb = "image_url",
        postUrl = "image_url",
        isWatched = false
    ), UsersStory(
        storyId = "s2",
        userId = "u1",
        createdAt = 1717939834921,
        postThumb = "image_url",
        postUrl = "image_url",
        isWatched = true
    ), UsersStory(
        storyId = "s3",
        userId = "u1",
        createdAt = 1717939834921,
        postThumb = "image_url",
        postUrl = "image_url",
        isWatched = true
    )
    )
    @Before
    fun setUp() {
        useCase = mockk()
        dispatcherProvider = TestDispatcherProvider(mainDispatcherRule.testDispatcher)
        every { useCase.getUsersPost() } returns flowOf(PagingData.empty())
        viewModel = UserProfileViewModel(useCase, dispatcherProvider)
    }

    @Test
    fun userProfileStateTest() = runTest {
        every { useCase.getUserProfile() } returns flowOf(user)
        viewModel.getUsersProfile()
        viewModel.userProfileState.test {
            assertEquals(UiState.Loading, awaitItem())   // first emission
            assertEquals(UiState.Success(user), awaitItem()) // second emission
            cancelAndIgnoreRemainingEvents()
        }
    }
    @Test
    fun userStoryStateTest() = runTest {
        every { useCase.getUserStory() } returns flowOf(storyList)
        viewModel.getUsersStory()
        viewModel.usersStoryState.test {
            assertEquals(UiState.Loading, awaitItem())   // first emission
            assertEquals(UiState.Success(storyList), awaitItem()) // second emission
            cancelAndIgnoreRemainingEvents()
        }
    }
    @After
    fun tearDown() {
        unmockkAll()
    }
}