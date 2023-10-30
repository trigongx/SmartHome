package com.example.smart_home.domain.repositories

import com.example.smart_home.data.storage.models.Note

interface NoteRepository {
    suspend fun getAllNotes():List<Note>
}