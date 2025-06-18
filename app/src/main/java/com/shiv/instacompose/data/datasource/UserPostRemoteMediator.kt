package com.shiv.instacompose.data.datasource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import coil.network.HttpException
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.local.entity.RemoteKeys
import com.shiv.instacompose.data.local.entity.UsersPostEntity
import com.shiv.instacompose.data.mapper.toUsersPostEntity
import com.shiv.instacompose.data.remote.api.UserApiService
import okio.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class UserPostRemoteMediator @Inject constructor(
    private val apiService: UserApiService,
    private val appDatabase: AppDatabase
) : RemoteMediator<Int, UsersPostEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UsersPostEntity>
    ): MediatorResult {

        return try {
            val page: Int = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1)?:1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    remoteKeys?.prevKey?: return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    remoteKeys?.nextKey?: return MediatorResult.Success(endOfPaginationReached = remoteKeys!=null)
                }
            }

            val userPostApiResponse = apiService.getUserPosts(page = page, 20)
            val endOfPagination = userPostApiResponse.posts.isEmpty()
            appDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    appDatabase.getRemoteKeyDao().clearAllKeys()
                    appDatabase.getUserPostDao().clearAllPost()
                }
                val preKey = if (page > 1) page - 1 else null
                val nextKey = if (endOfPagination) null else page + 1
                val remoteKeys = userPostApiResponse.posts.map {
                    RemoteKeys(
                        postId = it.postId,
                        prevKey = preKey,
                        currentPage = page,
                        nextKey = nextKey
                    )
                }
                appDatabase.getRemoteKeyDao().insertAll(remoteKeys)
                appDatabase.getUserPostDao().insertAll(
                    userPostApiResponse.toUsersPostEntity().onEach { post -> post.page = page })
            }
            MediatorResult.Success(endOfPaginationReached = endOfPagination)
        } catch (ex: IOException) {
            MediatorResult.Error(ex)
        } catch (ex: HttpException) {
            MediatorResult.Error(ex)
        }

    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, UsersPostEntity>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.postId?.let { id ->
                appDatabase.getRemoteKeyDao().getRemoteKeyByPostId(id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, UsersPostEntity>): RemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let { post ->
            appDatabase.getRemoteKeyDao().getRemoteKeyByPostId(post.postId)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, UsersPostEntity>): RemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()?.let { post ->
            appDatabase.getRemoteKeyDao().getRemoteKeyByPostId(post.postId)
        }
    }
}