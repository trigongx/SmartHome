package com.example.smart_home.core.network

import com.example.smart_home.data.storage.models.CameraModelDTO
import com.example.smart_home.data.storage.models.DoorModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras")
    suspend fun getCameras(): Response<CameraModelDTO>

    @GET("doors")
    suspend fun getDoors(): Response<DoorModelDTO>
}