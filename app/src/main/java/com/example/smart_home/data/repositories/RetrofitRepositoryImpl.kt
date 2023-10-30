package com.example.smart_home.data.repositories

import com.example.smart_home.data.storage.RetrofitStorage
import com.example.smart_home.data.storage.models.CameraModelDTO
import com.example.smart_home.data.storage.models.DoorModelDTO
import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.models.DoorModel
import com.example.smart_home.domain.repositories.RetrofitRepository

class RetrofitRepositoryImpl(private val retrofitStorage: RetrofitStorage): RetrofitRepository {

    override suspend fun getCameras(): Result<CameraModel> {
        return mapToCameraModel(retrofitStorage.getCameras())
    }

    override suspend fun getDoors(): Result<DoorModel> {
        return mapToDoorModel(retrofitStorage.getDoors())
    }

    private fun mapToDoorModel(doorsModel: DoorModelDTO): Result<DoorModel> {
        val doorsModelData = mutableListOf<DoorModel.Data>()
        doorsModel.data.mapTo(doorsModelData){
            DoorModel.Data(it.favorites,it.id,it.name,it.room?:"",it.snapshot)
        }
        return Result.success(DoorModel(doorsModelData,doorsModel.success))
    }

    private fun mapToCameraModel(cameraModel: CameraModelDTO): Result<CameraModel> {
        val camerasModelDataCameras = mutableListOf<CameraModel.Data.Camera>()
        cameraModel.data.cameras.mapTo(camerasModelDataCameras){
            CameraModel.Data.Camera(it.favorites,it.id,it.name,it.rec,it.room?:"",it.snapshot)
        }
        val camerasModelData = CameraModel.Data(camerasModelDataCameras.toList(),cameraModel.data.room)
        return Result.success(CameraModel(camerasModelData,cameraModel.success))
    }


}