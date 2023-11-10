package com.example.smart_home.data.local.storage

import com.example.smart_home.data.local.storage.models.NoteDTO

interface RoomStorage {
    suspend fun getAllNotes():List<NoteDTO>

}