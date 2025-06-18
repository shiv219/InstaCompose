package com.shiv.instacompose.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post")
data class UsersPostEntity(
    @PrimaryKey
    val postId: String,
    val userId: String,
    val createdAt: Long,
    val postThumb: String,
    val postUrl: String,
    var page:Int
)
