package com.shiv.instacompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shiv.instacompose.data.local.entity.UserProfileEntity

@Dao
interface UserProfileDao {

    @Upsert
    fun insertUser(userProfileEntity: UserProfileEntity)

    @Query("Select * From profile")
    fun getUserDetail(): UserProfileEntity

}