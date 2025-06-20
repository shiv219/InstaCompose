package com.shiv.instacompose.data.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.shiv.instacompose.data.local.db.AppDatabase
import com.shiv.instacompose.data.remote.api.UserApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Putting BASE_URL here as const for now for simplicity
 * later we can add it in BuildConfig for product flavours
 */
const val BASE_URL = "https://picsum.photos"
@Module
@InstallIn(SingletonComponent::class)
class DataProviderModule {
    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java,"insta_compose.db").build()


    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory =
        GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRetrofitClient(
        converterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(converterFactory).build()
    }

    @Provides
    @Singleton
    fun provideUserApiService(retrofit: Retrofit): UserApiService {
        return retrofit.create(UserApiService::class.java)
    }
}