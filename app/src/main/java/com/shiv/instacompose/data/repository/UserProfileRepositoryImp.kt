package com.shiv.instacompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.google.gson.reflect.TypeToken
import com.shiv.instacompose.data.datasource.UserPostRemoteMediator
import com.shiv.instacompose.data.filereader.JsonProvider
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.mapper.toEntity
import com.shiv.instacompose.data.mapper.toUserProfile
import com.shiv.instacompose.data.mapper.toUserProfileEntity
import com.shiv.instacompose.data.mapper.toUserStory
import com.shiv.instacompose.data.mapper.toUsersPost
import com.shiv.instacompose.data.remote.api.UserApiService
import com.shiv.instacompose.data.remote.dtomodel.UserProfileDto
import com.shiv.instacompose.data.remote.dtomodel.UserStoryDto
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepositoryImp @Inject constructor(
    private val userApiService: UserApiService,
    private val appDatabase: AppDatabase,
    private val userPostRemoteMediator: UserPostRemoteMediator,
    private val jsonProvider: JsonProvider
) : UserProfileRepository {
    /**
     * function to get user details from local db
     */
    override fun getUserProfile(): Flow<UserProfile> {
        return appDatabase.getUserProfileDao().getUserDetail().map { it.toUserProfile()}
    }
    /** function used for refreshing the user data from api call
     * Since we don't have an api here hence adding mock response
     */
    override fun refreshUserProfile() {
        val userDto:UserProfileDto = jsonProvider.fromFile(fileName = "user_profile.json",UserProfileDto::class.java)
        appDatabase.getUserProfileDao().insertUser(userDto.toUserProfileEntity())
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsersPost(): Flow<PagingData<UsersPost>> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = userPostRemoteMediator,
            pagingSourceFactory = {appDatabase.getUserPostDao().getUsersPostPagingSource()}
        ).flow.map { pagingData->
            pagingData.map { it.toUsersPost() }
        }
    }

    /**
     * function to get user's story from local db
     */
    override fun getUsersStory(): Flow<List<UsersStory>> {
        return appDatabase.getStoryDao().getUsersStory().map { it.toUserStory() }
    }
    /** function used for refreshing the user's story data from api call
     * Since we don't have an api here hence adding mock response
     */
    override fun refreshUsersStory() {
        val type = object : TypeToken<List<UserStoryDto>>() {}.type
        val userStoryDto :  List<UserStoryDto>  = jsonProvider.fromFile(fileName = "story.json", type)
        appDatabase.getStoryDao().insertStory(userStoryDto.toEntity())
    }
}