package com.shiv.instacompose.ui.screen.fake

import androidx.paging.PagingData
import com.google.gson.reflect.TypeToken
import com.shiv.instacompose.data.filereader.JsonProvider
import com.shiv.instacompose.domain.model.UserProfile
import com.shiv.instacompose.domain.model.UsersPost
import com.shiv.instacompose.domain.model.UsersStory
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class FakeUserProfileUseCase @Inject constructor(val jsonProvider: JsonProvider):UserProfileUseCase {
    override fun getUserProfile(): Flow<UserProfile> {
        val user: UserProfile = jsonProvider.fromFile(fileName = "user_profile.json",
            UserProfile::class.java)

        return flowOf(user)
    }

    override fun refreshUserProfile() {

    }

    override fun getUsersPost(): Flow<PagingData<UsersPost>> {
     return  flowOf(
         PagingData.from(
             listOf(
                 UsersPost(
                     postId = "post1",
                     postThumb = "imageUrl",
                     userId = "user1",
                     createdAt = 0L,
                     postUrl = "imageUrl"
                 ), UsersPost(
                     postId = "post2",
                     postThumb = "imageUrl",
                     userId = "user1",
                     createdAt = 0L,
                     postUrl = "imageUrl"
                 ), UsersPost(
                     postId = "post3",
                     postThumb = "imageUrl",
                     userId = "user1",
                     createdAt = 0L,
                     postUrl = "imageUrl"
                 ), UsersPost(
                     postId = "post4",
                     postThumb = "imageUrl",
                     userId = "user1",
                     createdAt = 0L,
                     postUrl = "imageUrl"
                 )
             )
         )
     )
    }

    override fun getUserStory(): Flow<List<UsersStory>> {
        val type = object : TypeToken<List<UsersStory>>() {}.type
        val userStory :  List<UsersStory>  = jsonProvider.fromFile(fileName = "story.json", type)
      return flowOf(userStory)
    }

    override fun refreshUserStory() {

    }

}