package com.shiv.instacompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "story")
data class UsersStoryEntity(
    @PrimaryKey
    val storyId: String,
    val userId: String,
    val createdAt: Long,
    val postThumb: String,
    val postUrl: String,
    val isWatched: Boolean
)