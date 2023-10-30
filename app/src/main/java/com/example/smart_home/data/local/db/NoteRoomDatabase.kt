package com.example.smart_home.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smart_home.data.local.dao.NoteDao
import com.example.smart_home.data.storage.models.Note

@Database(entities = [Note::class],version = 1)
abstract class NoteRoomDatabase: RoomDatabase() {

    abstract fun getDao():NoteDao

}