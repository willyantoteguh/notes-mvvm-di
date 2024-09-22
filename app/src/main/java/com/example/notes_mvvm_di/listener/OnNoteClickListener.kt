package com.example.notes_mvvm_di.listener

import com.example.notes_mvvm_di.data.model.Note

interface OnNoteClickListener {
    fun onDelete(note: Note)
}