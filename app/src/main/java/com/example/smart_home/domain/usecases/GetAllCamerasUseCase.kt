package com.example.smart_home.domain.usecases

import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.repositories.RetrofitRepository

class GetAllCamerasUseCase(private val retrofitRepository: RetrofitRepository) {

    suspend fun executeRequest():Result<CameraModel> = retrofitRepository.getCameras()
}