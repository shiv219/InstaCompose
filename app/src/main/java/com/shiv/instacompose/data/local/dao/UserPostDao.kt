package com.shiv.instacompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.shiv.instacompose.data.local.entity.UsersPostEntity

@Dao
interface UserPostDao {

    @Upsert
    fun insertAll(remoteKey: List<UsersPostEntity>)

    @Query("Select * From post")
    fun getUsersPostPagingSource(): PagingSource<Int, UsersPostEntity>

    @Query("Delete From post")
    fun clearAllPost()

}