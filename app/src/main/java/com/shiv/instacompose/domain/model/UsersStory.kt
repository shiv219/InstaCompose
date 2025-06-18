package com.shiv.instacompose.domain.model

data class UsersStory(
    val storyId: String,
    val userId: String,
    val createdAt: Long,
    val postThumb: String,
    val postUrl: String,
    val isWatched: Boolean
)