package com.example.notes_mvvm_di

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notes_mvvm_di.data.model.Note


@Database(entities = arrayOf(Note::class), version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}