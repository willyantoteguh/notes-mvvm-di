package com.example.notes_mvvm_di.repository

import com.example.notes_mvvm_di.NoteDao
import com.example.notes_mvvm_di.data.model.Note
import javax.inject.Inject

class NoteRepository @Inject constructor (val noteDao: NoteDao) {
    fun getAll() = noteDao.getAll()

    suspend fun insert(note: Note) = noteDao.insert(note)

    suspend fun delete(note: Note) = noteDao.delete(note)

    suspend fun update(note: Note) = noteDao.update(note)
}