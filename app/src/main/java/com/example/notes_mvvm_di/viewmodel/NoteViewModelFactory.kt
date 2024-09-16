package com.example.notes_mvvm_di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes_mvvm_di.repository.NoteRepository

// Sebagai penyimpanan object view model, biar tidak membuat dari awal
class NoteViewModelFactory(private val noteRepository: NoteRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteViewModel(noteRepository) as T
    }
}