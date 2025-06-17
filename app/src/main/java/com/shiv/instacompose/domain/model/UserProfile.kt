package com.shiv.instacompose.domain.model

data class UserProfile(
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
