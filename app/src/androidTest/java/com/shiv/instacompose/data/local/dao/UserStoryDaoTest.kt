package com.shiv.instacompose.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.local.entity.UsersStoryEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UserStoryDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var dao: UsersStoryDao

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = appDatabase.getStoryDao()
    }

    @Test
    fun getUserStory_throws_empty_list_initially_when_dab_has_no_data() = runTest {
        val result = dao.getUsersStory().first()
        assertTrue(result.isEmpty())
    }

    @Test
    fun insertUserStory_then_check_if_value_exists() = runTest {
        val storyList = listOf(
            UsersStoryEntity(
                storyId = "s1",
                userId = "u1",
                createdAt = 1717939834921,
                postThumb = "image_url",
                postUrl = "image_url",
                isWatched = false
            ), UsersStoryEntity(
                storyId = "s2",
                userId = "u1",
                createdAt = 1717939834921,
                postThumb = "image_url",
                postUrl = "image_url",
                isWatched = true
            ), UsersStoryEntity(
                storyId = "s3",
                userId = "u1",
                createdAt = 1717939834921,
                postThumb = "image_url",
                postUrl = "image_url",
                isWatched = true
            )
        )
        dao.insertStory(storyList)

        val result = dao.getUsersStory().first()
        assertTrue(result.isNotEmpty())
        result.forEachIndexed { index, item ->
            assertEquals(storyList[index].storyId, item.storyId)
        }

    }
    @Test
    fun delete_story_then_check_if_value_exists() = runTest {
        val storyList = listOf(
            UsersStoryEntity(
                storyId = "s1",
                userId = "u1",
                createdAt = 1717939834921,
                postThumb = "image_url",
                postUrl = "image_url",
                isWatched = false
            ), UsersStoryEntity(
                storyId = "s2",
                userId = "u1",
                createdAt = 1717939834921,
                postThumb = "image_url",
                postUrl = "image_url",
                isWatched = true
            ), UsersStoryEntity(
                storyId = "s3",
                userId = "u1",
                createdAt = 1717939834921,
                postThumb = "image_url",
                postUrl = "image_url",
                isWatched = true
            )
        )
        dao.insertStory(storyList)

        val result = dao.getUsersStory().first()
        assertTrue(result.isNotEmpty())
        dao.clearAllStory()
        val clearedResult = dao.getUsersStory().first()
        assertTrue(clearedResult.isEmpty())
    }

    @After
    fun clear() {
        appDatabase.close()
    }

}