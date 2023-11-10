package com.example.smart_home.core.di

import android.content.Context
import androidx.room.Room
import com.example.smart_home.data.local.dao.NoteDao
import com.example.smart_home.data.local.db.NoteRoomDatabase
import com.example.smart_home.data.local.storage.RoomStorage
import com.example.smart_home.data.local.storage.RoomStorageImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = NoteRoomDatabase::class.java,
        name = "notes"
    ).build()

    @Provides
    fun provideNoteDao(database: NoteRoomDatabase) = database.getDao()

    @Provides
    fun provideRoomStorage(noteDao: NoteDao):RoomStorage = RoomStorageImpl(noteDao)
}