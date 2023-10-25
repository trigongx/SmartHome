package com.example.smart_home.presentation.cameras

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smart_home.core.base.BaseViewModel
import com.example.smart_home.data.model.CameraModel
import com.example.smart_home.domain.repository.Repository

class CamerasViewModel(private val repository: Repository): BaseViewModel() {

    private val _cameras = MutableLiveData<CameraModel>()
    val cameras:LiveData<CameraModel> = _cameras

    fun getCameras(){
        doOperation(
            operation = { repository.getCameras() },
            success = {_cameras.postValue(it)}
        )
    }
}