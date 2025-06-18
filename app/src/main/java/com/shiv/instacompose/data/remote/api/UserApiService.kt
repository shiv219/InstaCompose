package com.shiv.instacompose.data.remote.api

import com.shiv.instacompose.data.common.UserApiEndPoint
import com.shiv.instacompose.data.remote.dtomodel.TimelineDto
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET(UserApiEndPoint.USER_POST)
    suspend fun getUserPosts(@Query("page") page: Int, @Query("limit") limit: Int): TimelineDto
}