package com.shiv.instacompose.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shiv.instacompose.data.local.dao.RemoteKeyDao
import com.shiv.instacompose.data.local.dao.UserPostDao
import com.shiv.instacompose.data.local.dao.UserProfileDao
import com.shiv.instacompose.data.local.entity.RemoteKeys
import com.shiv.instacompose.data.local.entity.UsersPostEntity
import com.shiv.instacompose.data.local.entity.UserProfileEntity

@Database(entities = [UsersPostEntity::class, UserProfileEntity::class, RemoteKeys::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserProfileDao(): UserProfileDao
    abstract fun getUserPostDao(): UserPostDao
    abstract fun getRemoteKeyDao(): RemoteKeyDao
}