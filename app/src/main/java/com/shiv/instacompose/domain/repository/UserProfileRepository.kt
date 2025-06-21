package com.shiv.instacompose.domain.repository

import androidx.paging.PagingData
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import com.shiv.instacompose.domain.model.UsersStory
import kotlinx.coroutines.flow.Flow

interface UserProfileRepository {
    fun getUserProfile(): Flow<UserProfile?>
    fun refreshUserProfile()
    fun getUsersPost(): Flow<PagingData<UsersPost>>

    /**
     * returning as a list for simplicity we can enhance it later
     * if required
     */
    fun getUsersStory(): Flow<List<UsersStory>>
    fun refreshUsersStory()
}