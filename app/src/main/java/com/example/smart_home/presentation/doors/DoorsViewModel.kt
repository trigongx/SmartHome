package com.example.smart_home.presentation.doors

import com.example.smart_home.core.base.BaseViewModel
import com.example.smart_home.domain.models.DoorModel
import com.example.smart_home.domain.usecases.GetAllDoorsUseCase
import com.example.smart_home.presentation.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(private val getAllDoors: GetAllDoorsUseCase):BaseViewModel<DoorModel>() {
    private val _doors = MutableStateFlow<UiState<List<DoorModel>>>(UiState.Empty())
    val doors: StateFlow<UiState<List<DoorModel>>> = _doors

    suspend fun getDoors() = doOperation(
        operation = { getAllDoors.executeRequest() }
    )
}
