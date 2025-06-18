package com.shiv.instacompose.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.shiv.instacompose.data.datasource.UserPostRemoteMediator
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.mapper.toUsersPost
import com.shiv.instacompose.data.remote.api.UserApiService
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import com.shiv.instacompose.domain.repository.UserProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserProfileRepositoryImp @Inject constructor(
    private val userApiService: UserApiService,
    private val appDatabase: AppDatabase,
    private val userPostRemoteMediator: UserPostRemoteMediator
) : UserProfileRepository {
    override fun getUserProfile(): Flow<UserProfile> {
        return flow { }
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
}