package com.shiv.instacompose.data.remote

import com.shiv.instacompose.data.common.UserApiEndPoint
import com.shiv.instacompose.domain.model.TimeLine
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApiService {

    @GET(UserApiEndPoint.USER_POST)
    suspend fun getUserPosts(@Query("page") page: String, @Query("limit") limit: String): TimeLine
}