package com.example.smart_home.data.local.storage

import com.example.smart_home.data.local.dao.NoteDao
import com.example.smart_home.data.local.storage.models.NoteDTO

class RoomStorageImpl(private val dao: NoteDao):RoomStorage {
    override suspend fun getAllNotes(): List<NoteDTO> = dao.getAllNotes()
}