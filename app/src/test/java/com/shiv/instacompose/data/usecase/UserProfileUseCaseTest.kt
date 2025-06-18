package com.shiv.instacompose.data.usecase

import app.cash.turbine.test
import com.shiv.instacompose.data.local.entity.UserProfileEntity
import com.shiv.instacompose.data.local.entity.UsersStoryEntity
import com.shiv.instacompose.data.mapper.toUserProfile
import com.shiv.instacompose.data.mapper.toUserStory
import com.shiv.instacompose.data.repository.UserProfileRepositoryImp
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.domain.repository.UserProfileRepository
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verifyCount
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class UserProfileUseCaseTest {
    private lateinit var repository: UserProfileRepository
    private lateinit var useCaseImp: UserProfileUseCase

    @Before
    fun setUp() {
        repository = mockk<UserProfileRepositoryImp>()
        useCaseImp = UserProfileUseCaseImp(repository)
        every { repository.refreshUserProfile() } just Runs
    }

    @Test
    fun `getUserProfile emits user detail`() = runTest {
        val user = UserProfile(
            id = "1",
            name = "Shiv",
            userName = "shiv_yadav",
            profileThumbUrl = "image_url",
            profileImageUrl = "image_url",
            postsCount = "30",
            followerCount = "490",
            followingCount = "789",
            bio = "Software Engineer"
        )
        every { repository.getUserProfile() } returns flowOf(user)
        useCaseImp.getUserProfile().test {
            val item = awaitItem()
            assertEquals(user, item)
            cancelAndConsumeRemainingEvents()
        }
    }
    @Test
    fun `getStoryDetails emits list of users story`() = runTest{
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

        every { repository.getUsersStory() } returns flowOf(storyList)

        repository.getUsersStory().test {
            val item = awaitItem()
            val userStory = storyList
            assertEquals(userStory, item)
            item.forEachIndexed {index, usersStory ->
                assertEquals(userStory[index].storyId,usersStory.storyId)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `refresh users story`(){
        every { repository.refreshUsersStory() } just Runs
        verifyCount { repository.refreshUsersStory() }
    }
    @Test
    fun `refresh users profile details`(){
        every { repository.refreshUserProfile() } just Runs
        verifyCount { repository.refreshUserProfile() }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}