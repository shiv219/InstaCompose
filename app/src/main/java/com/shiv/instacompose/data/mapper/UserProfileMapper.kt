package com.shiv.instacompose.data.mapper

import com.shiv.instacompose.data.local.entity.UsersPostEntity
import com.shiv.instacompose.data.remote.dtomodel.UsersPostDto
import com.shiv.instacompose.domain.model.UsersPost


fun List<UsersPostDto>.toUsersPostEntity(): List<UsersPostEntity> {
    val list = arrayListOf<UsersPostEntity>()
    this.forEach {
        list.add(
            UsersPostEntity(
                postId = it.id,
                userId = it.author,
                createdAt = 0L,
                postThumb = it.download_url,
                postUrl = it.download_url,
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

