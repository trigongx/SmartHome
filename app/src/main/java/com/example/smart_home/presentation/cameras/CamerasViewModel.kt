package com.example.smart_home.presentation.cameras

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smart_home.core.base.BaseViewModel
import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.usecases.GetAllCamerasUseCase

class CamerasViewModel(private val getCameras:GetAllCamerasUseCase): BaseViewModel() {

    private val _cameras = MutableLiveData<CameraModel>()
    val cameras:LiveData<CameraModel> = _cameras

    fun getCameras(){
        doOperation(
            operation = { getCameras.executeRequest() },
            success = {_cameras.postValue(it)}
        )
    }
}