package com.example.smart_home.data.storage

import com.example.smart_home.data.storage.models.CameraModelDTO
import com.example.smart_home.data.storage.models.DoorModelDTO

interface RetrofitStorage {

    suspend fun getCameras():CameraModelDTO

    suspend fun getDoors(): DoorModelDTO
}