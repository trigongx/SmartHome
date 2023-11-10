package com.example.smart_home.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smart_home.data.local.storage.models.NoteDTO

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getAllNotes():List<NoteDTO>

    @Query("SELECT * FROM notes WHERE title ==:title")
    fun getAllNotesByTitle(title:String):List<NoteDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(noteDTO: NoteDTO)

    @Insert
    fun insertAllNotes(noteDTOS: List<NoteDTO>)

    @Update
    fun updateNote(noteDTO: NoteDTO)

    @Delete
    fun deleteNote(noteDTO: NoteDTO)
}