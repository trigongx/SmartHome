package com.example.smart_home.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smart_home.data.storage.models.Note

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes():List<Note>

    @Query("SELECT * FROM notes WHERE title ==:title")
    fun getAllNotesByTitle(title:String):List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: Note)

    @Insert
    fun insertAllNotes(notes: List<Note>)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
}