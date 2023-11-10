package com.example.smart_home.data.remote.network

import com.example.smart_home.data.remote.storage.models.CameraModelDTO
import com.example.smart_home.data.remote.storage.models.DoorModelDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras")
    suspend fun getCameras(): Response<CameraModelDTO>

    @GET("doors")
    suspend fun getDoors(): Response<DoorModelDTO>
}