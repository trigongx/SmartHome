package com.example.smart_home.domain.repository

import com.example.smart_home.core.network.RemoteDataSource
import com.example.smart_home.data.model.CameraModel
import com.example.smart_home.data.model.DoorModel

class Repository(private val remoteDataSource: RemoteDataSource) {
    suspend fun getCameras():Result<CameraModel>{
        return remoteDataSource.getCameras()
    }
    suspend fun getDoors():Result<DoorModel>{
        return remoteDataSource.getDoors()
    }
}