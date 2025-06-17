package com.shiv.instacompose.data.usecase

import com.shiv.instacompose.domain.model.TimeLine
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.repository.UserProfileRepository
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileUseCaseImp @Inject constructor (private val repository: UserProfileRepository): UserProfileUseCase {
    override fun getUserProfile(): Flow<UserProfile> {
        return flow {  }
    }

    override fun getUsersPost(): Flow<TimeLine> {
    return flow {  }
    }


}