package com.shiv.instacompose.data.mapper

import com.shiv.instacompose.data.local.entity.UsersStoryEntity
import com.shiv.instacompose.data.remote.dtomodel.UserStoryDto
import com.shiv.instacompose.domain.model.UsersStory

fun UserStoryDto.toEntity(): UsersStoryEntity{
    return UsersStoryEntity(
        storyId = storyId,
        userId = userId,
        createdAt = createdAt,
        postThumb = postThumb,
        postUrl = postUrl,
        isWatched =isWatched
    )
}
fun UsersStoryEntity.toUserStory(): UsersStory {
    return UsersStory(
        storyId = storyId,
        userId = userId,
        createdAt = createdAt,
        postThumb = postThumb,
        postUrl = postUrl,
        isWatched =isWatched
    )
}