package com.example.smart_home.data.utils

import com.example.smart_home.data.local.storage.models.NoteDTO
import com.example.smart_home.data.remote.storage.models.CameraModelDTO
import com.example.smart_home.data.remote.storage.models.DoorModelDTO
import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.models.DoorModel
import com.example.smart_home.domain.models.Note

fun CameraModelDTO.mapToCameraModel(): CameraModel{
    val camerasModelDataCameras = mutableListOf<CameraModel.Data.Camera>()
    this.data.cameras.mapTo(camerasModelDataCameras){
        CameraModel.Data.Camera(it.favorites,it.id, it.name, it.rec, it.room?:"", it.snapshot)
    }
    val camerasModelData = CameraModel.Data(camerasModelDataCameras.toList(),this.data.room)
    return CameraModel(camerasModelData,this.success)
}

fun DoorModelDTO.mapToDoorsModel(): DoorModel {
    val doorsModelData = mutableListOf<DoorModel.Data>()
    this.data.mapTo(doorsModelData) {
        DoorModel.Data(it.favorites, it.id, it.name, it.room?:"", it.snapshot)
    }
    return DoorModel(doorsModelData, this.success)
}

fun List<NoteDTO>.mapToNote():List<Note> = this.map{
    Note(
        id = it.id,
        title = it.title,
        description = it.description,
        isDone = it.isDone
    )
}

fun Note.mapToNoteDTO():NoteDTO = NoteDTO(
    id = this.id,
    title = this.title,
    description = this.description,
    isDone = this.isDone
)