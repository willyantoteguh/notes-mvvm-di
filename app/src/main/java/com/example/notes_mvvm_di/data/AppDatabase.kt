package com.example.notes_mvvm_di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes_mvvm_di.data.model.Note


@Database(entities = arrayOf(Note::class), version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "note_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance!!
        }
    }
}