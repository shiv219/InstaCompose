package com.shiv.instacompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shiv.instacompose.data.local.entity.UserProfileEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserProfileDao {

    @Upsert
    fun insertUser(userProfileEntity: UserProfileEntity)

    @Query("Select * From profile")
    fun getUserDetail(): Flow<UserProfileEntity>

    @Query("Delete From profile")
    fun clearProfile()
}