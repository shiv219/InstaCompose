package com.shiv.instacompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shiv.instacompose.data.local.entity.UsersStoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UsersStoryDao {

    @Upsert
    fun insertStory(story: List<UsersStoryEntity>)

    @Query("Select * From story")
    fun getUsersStory(): Flow<List<UsersStoryEntity>>

    @Query("Delete From story")
    fun clearAllStory()

}