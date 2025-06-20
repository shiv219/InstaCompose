package com.shiv.instacompose.ui.screen.fake

import com.shiv.instacompose.data.di.DataBindModule
import com.shiv.instacompose.data.filereader.JsonProvider
import com.shiv.instacompose.data.filereader.ResourceJsonProvider
import com.shiv.instacompose.domain.usecase.UserProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(components = [SingletonComponent::class],
    replaces = [DataBindModule::class])
interface FakeModule {

    @Binds
    fun provideUserProfileUseCase(userProfileUseCaseImp: FakeUserProfileUseCase): UserProfileUseCase

    @Binds
    fun provideJsonProvider(resourceJsonProvider: ResourceJsonProvider): JsonProvider

}