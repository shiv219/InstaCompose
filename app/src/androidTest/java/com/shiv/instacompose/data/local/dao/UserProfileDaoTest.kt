package com.shiv.instacompose.data.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.local.entity.UserProfileEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue


class UserProfileDaoTest {
    private lateinit var appDatabase: AppDatabase
    private lateinit var dao: UserProfileDao

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = appDatabase.getUserProfileDao()
    }

    @Test
    fun getUserProfile_throws_error_initially_when_dab_has_no_data() = runTest {
        val exception =  assertFailsWith<IllegalStateException> {
             dao.getUserDetail().firstOrNull()
         }
        assertTrue { exception.message!!.contains("The query result was empty") }
    }
    
    @Test
    fun insertUserProfileTest() = runTest{
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
        dao.insertUser(user)
        val result = dao.getUserDetail().first()
        assertEquals(user,result)
    }
    @Test
    fun insertUserProfile_then_deleteUser_Test() = runTest{
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
        dao.insertUser(user)
        val result = dao.getUserDetail().first()
        assertEquals(user,result)

        dao.clearProfile()

      val exception = assertFailsWith<IllegalStateException> {
           dao.getUserDetail().first()
       }
        assertTrue { exception.message!!.contains("The query result was empty") }
    }
    @Test
    fun insertUserProfile_then_update_User_Test() = runTest{
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
        dao.insertUser(user)
        val result = dao.getUserDetail().first()
        assertEquals("Shiv",result.name)

        val updatedUser = UserProfileEntity(
            id = "1",
            name = "Sameer",
            userName = "shiv_yadav",
            profileThumbUrl = "image_url",
            profileImageUrl = "image_url",
            postsCount = "30",
            followerCount = "490",
            followingCount = "789",
            bio = "Software Engineer"
        )
        dao.insertUser(updatedUser)
        val updatedResult = dao.getUserDetail().first()
        assertEquals("Sameer",updatedResult.name)
    }

    @After
    fun clear() {
    appDatabase.close()
    }

}