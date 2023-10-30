package com.example.smart_home.domain.usecases

import com.example.smart_home.domain.repositories.NoteRepository
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(private val noteRepository: NoteRepository) {

    suspend fun getAllNotes() = noteRepository.getAllNotes()
}