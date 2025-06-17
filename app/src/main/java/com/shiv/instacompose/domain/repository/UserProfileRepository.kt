package com.shiv.instacompose.domain.repository

import com.shiv.instacompose.domain.model.TimeLine
import com.shiv.instacompose.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    fun getUserProfile():Flow<UserProfile>
    fun getUsersPost(): Flow<TimeLine>
}