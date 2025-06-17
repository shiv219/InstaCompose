package com.shiv.instacompose.data.di

import com.shiv.instacompose.data.repository.UserProfileRepositoryImp
import com.shiv.instacompose.domain.repository.UserProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun provideUserProfileRepository(userProfileRepository: UserProfileRepositoryImp): UserProfileRepository


}