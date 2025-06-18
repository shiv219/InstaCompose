package com.shiv.instacompose.data.remote.dtomodel

data class UserProfileDto(
    val id: String,
    val name: String,
    val userName: String,
    val profileThumbUrl: String,
    val profileImageUrl: String,
    val postsCount: String,
    val followerCount: String,
    val followingCount: String,
    val bio: String
)