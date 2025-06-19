package com.shiv.instacompose.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shiv.instacompose.data.local.entity.RemoteKeys

@Dao
interface RemoteKeyDao {
    @Upsert
    fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("Select * From remote_key where postId = :id")
    suspend fun getRemoteKeyByPostId(id: String): RemoteKeys?

    @Query("Delete From remote_key")
    suspend fun clearAllKeys()
}