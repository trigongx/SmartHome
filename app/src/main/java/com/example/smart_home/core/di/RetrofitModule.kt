package com.example.smart_home.core.di

import com.example.smart_home.data.local.storage.RoomStorage
import com.example.smart_home.data.remote.network.ApiService
import com.example.smart_home.data.remote.network.RetrofitClient
import com.example.smart_home.data.remote.storage.RetrofitStorage
import com.example.smart_home.data.remote.storage.RetrofitStorageImpl
import com.example.smart_home.data.repositories.RetrofitRepositoryImpl
import com.example.smart_home.domain.repositories.RetrofitRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideRetrofitClient() = RetrofitClient()

    @Provides
    @Singleton
    fun provideApiService(retrofitClient: RetrofitClient) = retrofitClient.createApiService()

    @Provides
    @Singleton
    fun provideRetrofitStorage(apiService: ApiService) : RetrofitStorage = RetrofitStorageImpl(apiService)

    @Provides
    @Singleton
    fun provideRetrofitRepository(retrofitStorage: RetrofitStorage,roomStorage: RoomStorage): RetrofitRepository = RetrofitRepositoryImpl(retrofitStorage,roomStorage)

}