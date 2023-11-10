package com.example.smart_home.domain.repositories

import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.models.DoorModel
import com.example.smart_home.domain.models.Note
import com.example.smart_home.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RetrofitRepository {
    suspend fun getCameras(): Flow<Resource<CameraModel>>
    suspend fun getDoors():Flow<Resource<DoorModel>>
    suspend fun getAllNotes():Flow<Resource<List<Note>>>
}