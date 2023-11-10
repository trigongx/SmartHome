package com.example.smart_home.domain.usecases

import com.example.smart_home.domain.repositories.RetrofitRepository

class GetAllNotesUseCase (private val retrofitRepository: RetrofitRepository) {

    suspend fun executeRequest() = retrofitRepository.getAllNotes()
}