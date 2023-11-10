package com.example.smart_home.data.remote.storage

import com.example.smart_home.data.remote.storage.models.CameraModelDTO
import com.example.smart_home.data.remote.storage.models.DoorModelDTO

interface RetrofitStorage {

    suspend fun getCameras(): CameraModelDTO

    suspend fun getDoors(): DoorModelDTO
}