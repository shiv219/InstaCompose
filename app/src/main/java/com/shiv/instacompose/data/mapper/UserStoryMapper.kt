package com.shiv.instacompose.data.mapper

import com.shiv.instacompose.data.local.entity.UsersStoryEntity
import com.shiv.instacompose.data.remote.dtomodel.UserStoryDto
import com.shiv.instacompose.domain.model.UsersStory

fun List<UserStoryDto>.toEntity(): List<UsersStoryEntity> {

    val list = arrayListOf<UsersStoryEntity>()
    this.forEach {
        list.add(
            UsersStoryEntity(
                storyId = it.storyId,
                userId = it.userId,
                createdAt = it.createdAt,
                postThumb = it.postThumb,
                postUrl = it.postUrl,
                isWatched = it.isWatched
            )
        )
    }
    return list
}

fun List<UsersStoryEntity>.toUserStory(): List<UsersStory> {
    val list = arrayListOf<UsersStory>()
    this.forEach {
        list.add(
            UsersStory(
                storyId = it.storyId,
                userId = it.userId,
                createdAt = it.createdAt,
                postThumb = it.postThumb,
                postUrl = it.postUrl,
                isWatched = it.isWatched
            )
        )
    }
    return list
}