package com.example.smart_home.data.repository

import com.example.smart_home.data.local.storage.RoomStorage
import com.example.smart_home.data.remote.storage.RetrofitStorage
import com.example.smart_home.data.utils.mapToCameraModel
import com.example.smart_home.data.utils.mapToDoorsModel
import com.example.smart_home.data.utils.mapToNote
import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.models.DoorModel
import com.example.smart_home.domain.models.Note
import com.example.smart_home.domain.repository.RetrofitRepository
import com.example.smart_home.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RetrofitRepositoryImpl(
    private val retrofitStorage: RetrofitStorage,
    private val roomStorage: RoomStorage
) : RetrofitRepository {

    override suspend fun getCameras(): Flow<Resource<CameraModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = retrofitStorage.getCameras().mapToCameraModel()
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }

    override suspend fun getDoors(): Flow<Resource<DoorModel>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = retrofitStorage.getDoors().mapToDoorsModel()
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }

    override suspend fun getAllNotes(): Flow<Resource<List<Note>>> {
        return flow {
            emit(Resource.Loading())
            try {
                val data = roomStorage.getAllNotes().mapToNote()
                emit(Resource.Success(data))
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
        }
    }


}