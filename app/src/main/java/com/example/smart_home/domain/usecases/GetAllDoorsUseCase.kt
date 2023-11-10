package com.example.smart_home.domain.usecases

import com.example.smart_home.domain.repositories.RetrofitRepository

class GetAllDoorsUseCase(private val retrofitRepository: RetrofitRepository) {
    suspend fun executeRequest() = retrofitRepository.getDoors()
}