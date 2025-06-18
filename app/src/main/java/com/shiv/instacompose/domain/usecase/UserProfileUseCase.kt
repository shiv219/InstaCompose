package com.shiv.instacompose.domain.usecase

import androidx.paging.PagingData
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import kotlinx.coroutines.flow.Flow

interface UserProfileUseCase {
    fun getUserProfile(): Flow<UserProfile>
    fun getUsersPost(): Flow<PagingData<UsersPost>>
}