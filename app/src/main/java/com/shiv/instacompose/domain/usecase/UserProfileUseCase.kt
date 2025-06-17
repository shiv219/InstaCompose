package com.shiv.instacompose.domain.usecase

import com.shiv.instacompose.domain.model.TimeLine
import com.shiv.instacompose.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserProfileUseCase {
    fun getUserProfile(): Flow<UserProfile>
    fun getUsersPost(): Flow<TimeLine>
}