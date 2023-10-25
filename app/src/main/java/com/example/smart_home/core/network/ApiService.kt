package com.example.smart_home.core.network

import com.example.smart_home.data.model.CameraModel
import com.example.smart_home.data.model.DoorModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("cameras")
    suspend fun getCameras(): Response<CameraModel>

    @GET("doors")
    suspend fun getDoors(): Response<DoorModel>
}