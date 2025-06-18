package com.shiv.instacompose.domain.model


data class UsersPost(
    val postId: String,
    val userId: String,
    val createdAt:Long,
    val postThumb:String,
    val postUrl:String
)
