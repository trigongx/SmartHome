package com.example.smart_home.presentation.doors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.smart_home.core.base.BaseViewModel
import com.example.smart_home.data.model.DoorModel
import com.example.smart_home.presentation.activity.MainActivity.Companion.repository

class DoorsViewModel:BaseViewModel() {
    private val _doors = MutableLiveData<DoorModel>()
    val doors: LiveData<DoorModel> get() = _doors

    fun getDoors(){
        doOperation(
            operation = { repository.getDoors() },
            success = {_doors.postValue(it)}
        )
    }
}