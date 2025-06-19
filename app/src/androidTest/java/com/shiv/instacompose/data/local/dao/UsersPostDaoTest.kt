package com.shiv.instacompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.local.entity.UsersPostEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@HiltAndroidTest
class UsersPostDaoTest {

    @get:Rule
    val hitRule = HiltAndroidRule(this)

    private lateinit var appDatabase: AppDatabase
    private lateinit var dao: UserPostDao

    @Before
    fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = appDatabase.getUserPostDao()
    }

    @Test
    fun userPostPagingSource_returns_expected_data()= runTest{

        val post = (1..20).map {
            UsersPostEntity(
                postId = it.toString(),
                userId = "u1",
                postThumb = "img_$it",
                createdAt = System.currentTimeMillis(),
                postUrl = "img_$it",
                page = (it/10)+1
            )
        }
        dao.insertAll(post)

        val pagingSource = dao.getUsersPostPagingSource()
        val expected = post.take(10)

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 10,
                placeholdersEnabled = false
            )

        )
        assertTrue (result is PagingSource.LoadResult.Page)

        assertEquals(expected, result.data)
        assertEquals(null, result.prevKey)
        assertEquals(10, result.nextKey)

    }

    @After
    fun clear(){
        appDatabase.close()
    }
}