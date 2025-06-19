package com.shiv.instacompose.core

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {
    @Binds
    fun provideDispatcher(dispatchers: DefaultDispatchers):DispatcherProvider
}