package com.example.smart_home.presentation.doors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smart_home.core.base.BaseViewModel
import com.example.smart_home.domain.models.DoorModel
import com.example.smart_home.domain.usecases.GetAllDoorsUseCase

class DoorsViewModel(private val getAllDoorsUseCase: GetAllDoorsUseCase):BaseViewModel() {
    private val _doors = MutableLiveData<DoorModel>()
    val doors: LiveData<DoorModel> get() = _doors

    fun getDoors(){
        doOperation(
            operation = { getAllDoorsUseCase.executeRequest() },
            success = {_doors.postValue(it)}
        )
    }
}