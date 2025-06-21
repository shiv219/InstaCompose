package com.shiv.instacompose.data.usecase

import androidx.paging.PagingData
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.domain.repository.UserProfileRepository
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileUseCaseImp @Inject constructor(private val repository: UserProfileRepository) :
    UserProfileUseCase {
    override fun getUserProfile(): Flow<UserProfile?> {
        return repository.getUserProfile()
    }

    override fun refreshUserProfile() {
        repository.refreshUserProfile()
    }

    override fun getUsersPost(): Flow<PagingData<UsersPost>> {
        return repository.getUsersPost()
    }

    override fun getUserStory(): Flow<List<UsersStory>> {
      return repository.getUsersStory()
    }

    override fun refreshUserStory() {
        repository.refreshUsersStory()
    }


}