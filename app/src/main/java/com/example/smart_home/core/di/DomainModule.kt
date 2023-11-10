package com.example.smart_home.core.di

import com.example.smart_home.domain.repositories.RetrofitRepository
import com.example.smart_home.domain.usecases.GetAllCamerasUseCase
import com.example.smart_home.domain.usecases.GetAllDoorsUseCase
import com.example.smart_home.domain.usecases.GetAllNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DomainModule {

    @Provides
    fun provideGetCameraUseCase(retrofitRepository: RetrofitRepository) =
        GetAllCamerasUseCase(retrofitRepository)

    @Provides
    fun provideGetDoorsUseCase(retrofitRepository: RetrofitRepository) =
        GetAllDoorsUseCase(retrofitRepository)

    @Provides
    fun provideGetAllNotesUseCase(retrofitRepository: RetrofitRepository) =
        GetAllNotesUseCase(retrofitRepository)

}