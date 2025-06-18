package com.shiv.instacompose.data.remote.dtomodel

data class TimelineDto(
    val posts: List<UsersPostDto>
)
data class UsersPostDto(
    val postId: String,
    val userId: String,
    val createdAt:Long,
    val postThumb:String,
    val postUrl:String
)