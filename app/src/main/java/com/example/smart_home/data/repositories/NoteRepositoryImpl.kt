package com.example.smart_home.data.repositories

import com.example.smart_home.data.local.dao.NoteDao
import com.example.smart_home.data.storage.models.Note
import com.example.smart_home.domain.repositories.NoteRepository
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val dao:NoteDao) :NoteRepository {

    override suspend fun getAllNotes(): List<Note> {
        return dao.getAllNotes()
    }
}