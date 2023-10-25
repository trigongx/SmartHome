package com.example.smart_home.core.network

import com.example.smart_home.core.base.BaseDataSource
import com.example.smart_home.data.model.CameraModel
import com.example.smart_home.data.model.DoorModel

class RemoteDataSource(private val apiService: ApiService):BaseDataSource() {
    suspend fun getCameras():Result<CameraModel>{
        return getResult { apiService.getCameras() }
    }

    suspend fun getDoors():Result<DoorModel>{
        return getResult { apiService.getDoors() }
    }

}