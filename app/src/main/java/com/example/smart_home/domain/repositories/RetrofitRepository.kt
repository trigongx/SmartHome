package com.example.smart_home.domain.repositories

import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.models.DoorModel

interface RetrofitRepository {
    suspend fun getCameras(): Result<CameraModel>

    suspend fun getDoors():Result<DoorModel>
}