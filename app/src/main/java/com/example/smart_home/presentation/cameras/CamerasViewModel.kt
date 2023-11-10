package com.example.smart_home.presentation.cameras

import com.example.smart_home.core.base.BaseViewModel
import com.example.smart_home.domain.models.CameraModel
import com.example.smart_home.domain.usecases.GetAllCamerasUseCase
import com.example.smart_home.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class CamerasViewModel @Inject constructor(private val getCameras: GetAllCamerasUseCase) : BaseViewModel<CameraModel>() {

    private val _cameras = MutableStateFlow<UiState<List<CameraModel.Data.Camera>>>(UiState.Empty())
    val cameras: StateFlow<UiState<List<CameraModel.Data.Camera>>> = _cameras

    /*suspend fun getCameras() {
        viewModelScope.launch {
            getCameras.executeRequest().collect {
                when (it){
                    is Resource.Loading -> _cameras.value = UiState.Loading()
                    is Resource.Success ->
                        if (it.data == null){
                            _cameras.value = UiState.Empty()
                        } else {
                            _cameras.value = UiState.Success(it.data.data.cameras)
                        }

                    is Resource.Error -> _cameras.value = UiState.Error(it.message ?: "Error")
                }
            }
        }
    }*/
    suspend fun getCameras() = doOperation(
        operation = { getCameras.executeRequest()}
    )

    /*private val _cameras = MutableLiveData<CameraModel>()
    val cameras:LiveData<CameraModel> = _cameras*/


    /*fun getCameras(){
        doOperation(
            operation = { getCameras.executeRequest() },
            success = {_cameras.postValue(it)}
        )
    }*/
}