package com.shiv.instacompose.data.remote.dtomodel


data class UserStoryDto(
    val storyId: String,
    val userId: String,
    val createdAt: Long,
    val postThumb: String,
    val postUrl: String,
    val isWatched: Boolean
)
