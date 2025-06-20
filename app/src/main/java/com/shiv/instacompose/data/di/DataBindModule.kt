package com.shiv.instacompose.data.di

import com.shiv.instacompose.data.filereader.JsonProvider
import com.shiv.instacompose.data.filereader.ResourceJsonProvider
import com.shiv.instacompose.data.repository.UserProfileRepositoryImp
import com.shiv.instacompose.data.usecase.UserProfileUseCaseImp
import com.shiv.instacompose.domain.repository.UserProfileRepository
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataBindModule {

    @Binds
    fun provideUserProfileRepository(userProfileRepository: UserProfileRepositoryImp): UserProfileRepository

    @Binds
    fun provideUserProfileUseCase(userProfileUseCaseImp: UserProfileUseCaseImp): UserProfileUseCase

    @Binds
    fun provideJsonProvider(resourceJsonProvider: ResourceJsonProvider): JsonProvider
}