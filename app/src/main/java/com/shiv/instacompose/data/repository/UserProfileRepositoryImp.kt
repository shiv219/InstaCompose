package com.shiv.instacompose.data.repository

import com.shiv.instacompose.data.remote.UserApiService
import com.shiv.instacompose.domain.model.TimeLine
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepositoryImp @Inject constructor(private val userApiService: UserApiService) :
    UserProfileRepository {
    override fun getUserProfile(): Flow<UserProfile> {
        return flow { }
    }

    override fun getUsersPost(): Flow<TimeLine> {
        return flow { }
    }
}