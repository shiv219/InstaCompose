package com.shiv.instacompose.domain.model

data class TimeLine(
    val list: List<UsersPost>
)
data class UsersPost(
    val postId: String,
    val createdAt:Long,
    val postThumb:String,
    val postUrl:String
)
