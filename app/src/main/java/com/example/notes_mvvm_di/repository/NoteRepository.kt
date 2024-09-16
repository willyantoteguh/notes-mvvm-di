package com.example.notes_mvvm_di.repository

import com.example.notes_mvvm_di.AppDatabase
import com.example.notes_mvvm_di.data.model.Note

class NoteRepository(private val db: AppDatabase) {
    fun getAll() = db.noteDao().getAll()

    suspend fun insert(note: Note) = db.noteDao().insert(note)

    suspend fun delelete(note: Note) = db.noteDao().delete(note)

    suspend fun update(note: Note) = db.noteDao().update(note)
}