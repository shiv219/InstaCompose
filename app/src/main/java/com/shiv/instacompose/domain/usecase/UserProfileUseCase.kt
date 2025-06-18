package com.shiv.instacompose.domain.usecase

import androidx.paging.PagingData
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import com.shiv.instacompose.domain.model.UsersStory
import kotlinx.coroutines.flow.Flow

interface UserProfileUseCase {
    fun getUserProfile(): Flow<UserProfile>
    fun refreshUserProfile()
    fun getUsersPost(): Flow<PagingData<UsersPost>>
    fun getUserStory(): Flow<List<UsersStory>>
    fun refreshUserStory()
}