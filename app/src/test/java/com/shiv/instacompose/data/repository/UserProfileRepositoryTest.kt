package com.shiv.instacompose.data.repository

import app.cash.turbine.test
import com.google.gson.reflect.TypeToken
import com.shiv.instacompose.data.datasource.UserPostRemoteMediator
import com.shiv.instacompose.data.filereader.JsonProvider
import com.shiv.instacompose.data.filereader.ResourceJsonProvider
import com.shiv.instacompose.data.local.dao.UserProfileDao
import com.shiv.instacompose.data.local.dao.UsersStoryDao
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.local.entity.UserProfileEntity
import com.shiv.instacompose.data.local.entity.UsersStoryEntity
import com.shiv.instacompose.data.mapper.toEntity
import com.shiv.instacompose.data.mapper.toUserProfile
import com.shiv.instacompose.data.mapper.toUserProfileEntity
import com.shiv.instacompose.data.mapper.toUserStory
import com.shiv.instacompose.data.remote.api.UserApiService
import com.shiv.instacompose.data.remote.dtomodel.UserProfileDto
import com.shiv.instacompose.data.remote.dtomodel.UserStoryDto
import com.shiv.instacompose.domain.repository.UserProfileRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertFailsWith

class UserProfileRepositoryTest {
    private lateinit var apiService: UserApiService
    private lateinit var appDatabase: AppDatabase
    private lateinit var userPostRemoteMediator: UserPostRemoteMediator
    private lateinit var userProfileRepository: UserProfileRepository
    private lateinit var userProfileDao: UserProfileDao
    private lateinit var storyDao: UsersStoryDao
    private lateinit var jsonProvider: JsonProvider

    @Before
    fun setUp() {
        apiService = mockk()
        appDatabase = mockk()
        userProfileDao = spyk()
        storyDao = spyk()
        userPostRemoteMediator = mockk()
        jsonProvider = mockk<ResourceJsonProvider>()
        userProfileRepository = UserProfileRepositoryImp(
            userApiService = apiService,
            appDatabase = appDatabase,
            userPostRemoteMediator = userPostRemoteMediator,
           jsonProvider
        )
    }


    @Test
    fun `getUserProfile emits mapped user detail`() = runTest {
        val user = UserProfileEntity(
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
        every { appDatabase.getUserProfileDao() } returns userProfileDao
        every { userProfileDao.getUserDetail() } returns flowOf(user)
        userProfileRepository.getUserProfile().test {
            val item = awaitItem()
            assertEquals(user.toUserProfile(), item)
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `getUserProfile propagates DAO error`() = runTest {
        every { appDatabase.getUserProfileDao() } returns userProfileDao
        every { userProfileDao.getUserDetail() } returns flow {
            throw IllegalStateException("DB error")
        }
        val flow = userProfileRepository.getUserProfile()

        assertFailsWith<IllegalStateException> {
            flow.collect()
        }
    }

    @Test
    fun `refreshUserProfile inserts user from asset JSON`() = runTest {
        val dto = UserProfileDto(
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
        every { appDatabase.getUserProfileDao() } returns userProfileDao
        every<UserProfileDto> { jsonProvider.fromFile("user_profile.json", UserProfileDto::class.java) } returns dto

        userProfileRepository.refreshUserProfile()

        val expectedEntity = dto.toUserProfileEntity()
        verify{ userProfileDao.insertUser(expectedEntity)  }

    }

    @Test
    fun `getStoryDetails emits mapped list of users story`() = runTest{
        val storyList = listOf( UsersStoryEntity(
            storyId = "s1",
            userId = "u1",
            createdAt = 1717939834921,
            postThumb = "image_url",
            postUrl = "image_url",
            isWatched = false
        ),UsersStoryEntity(
            storyId = "s2",
            userId = "u1",
            createdAt = 1717939834921,
            postThumb = "image_url",
            postUrl = "image_url",
            isWatched = true
        ),UsersStoryEntity(
            storyId = "s3",
            userId = "u1",
            createdAt = 1717939834921,
            postThumb = "image_url",
            postUrl = "image_url",
            isWatched = true
        ))
        every { appDatabase.getStoryDao() } returns storyDao
        every { storyDao.getUsersStory() } returns flowOf(storyList)

        userProfileRepository.getUsersStory().test {
            val item = awaitItem()
            val userStory = storyList.toUserStory()
            assertEquals(userStory, item)
            item.forEachIndexed {index, usersStory ->
                assertEquals(userStory[index].storyId,usersStory.storyId)
            }
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `refreshUserStory inserts story from asset JSON`() = runTest {
        val storyList = listOf( UserStoryDto(
            storyId = "s1",
            userId = "u1",
            createdAt = 1717939834921,
            postThumb = "image_url",
            postUrl = "image_url",
            isWatched = false
        ),UserStoryDto(
            storyId = "s2",
            userId = "u1",
            createdAt = 1717939834921,
            postThumb = "image_url",
            postUrl = "image_url",
            isWatched = true
        ),UserStoryDto(
            storyId = "s3",
            userId = "u1",
            createdAt = 1717939834921,
            postThumb = "image_url",
            postUrl = "image_url",
            isWatched = true
        ))
        every { appDatabase.getStoryDao() } returns storyDao
        every<List<UserStoryDto>> { jsonProvider.fromFile("story.json", object : TypeToken<List<UserStoryDto>>() {}.type) } returns storyList

        userProfileRepository.refreshUsersStory()

        val expectedEntity = storyList.toEntity()
        verify{ storyDao.insertStory(expectedEntity)  }

    }

    @Test
    fun `getUserStory propagates DAO error`() = runTest {
        every { appDatabase.getStoryDao() } returns storyDao
        every { storyDao.getUsersStory() } returns flow{
            throw IllegalStateException("DB Error")
     }
        val flow = userProfileRepository.getUsersStory()
        assertFailsWith<IllegalStateException> {
            flow.collect()
        }
    }

    @After
    fun tearDown() {
        unmockkAll()
    }
}