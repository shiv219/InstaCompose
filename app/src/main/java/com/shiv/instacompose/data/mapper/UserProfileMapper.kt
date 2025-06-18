package com.shiv.instacompose.data.mapper

import com.shiv.instacompose.data.local.entity.UsersPostEntity
import com.shiv.instacompose.data.remote.dtomodel.TimelineDto
import com.shiv.instacompose.domain.model.UsersPost


fun TimelineDto.toUsersPostEntity(): List<UsersPostEntity> {
    val list = arrayListOf<UsersPostEntity>()
    posts.forEach {
        list.add(
            UsersPostEntity(
                postId = it.postId,
                userId = it.userId,
                createdAt = it.createdAt,
                postThumb = it.postThumb,
                postUrl = it.postUrl,
                page = 0
            )
        )
    }

    return list
}

fun UsersPostEntity.toUsersPost(): UsersPost {
    return UsersPost(
        postId = postId,
        userId = userId,
        createdAt = createdAt,
        postThumb = postThumb,
        postUrl = postUrl
    )


}

