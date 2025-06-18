package com.shiv.instacompose.data.mapper

import com.shiv.instacompose.data.local.entity.UserProfileEntity
import com.shiv.instacompose.data.remote.dtomodel.UserProfileDto
import com.shiv.instacompose.domain.model.UserProfile

fun UserProfileDto.toUserProfileEntity() : UserProfileEntity {
    return UserProfileEntity(
        id = id,
        name = name,
        userName = userName,
        profileThumbUrl = profileThumbUrl,
        profileImageUrl = profileImageUrl,
        postsCount = postsCount,
        followerCount = followerCount,
        followingCount = followingCount,
        bio = bio
    )
}

fun UserProfileEntity.toUserProfile(): UserProfile{
    return UserProfile(
        id = id,
        name = name,
        userName = userName,
        profileThumbUrl = profileThumbUrl,
        profileImageUrl = profileImageUrl,
        postsCount = postsCount,
        followerCount = followerCount,
        followingCount = followingCount,
        bio = bio
    )
}