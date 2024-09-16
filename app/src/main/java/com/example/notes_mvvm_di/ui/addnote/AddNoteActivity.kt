package com.example.notes_mvvm_di.ui.addnote

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.notes_mvvm_di.AppDatabase
import com.example.notes_mvvm_di.data.model.Note
import com.example.notes_mvvm_di.databinding.ActivityAddBinding
import com.example.notes_mvvm_di.repository.NoteRepository
import com.example.notes_mvvm_di.viewmodel.NoteViewModel
import com.example.notes_mvvm_di.viewmodel.NoteViewModelFactory

class AddNoteActivity: AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewModel()

        binding.imgCheck.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()

            if (title.isNullOrEmpty() || description.isNullOrEmpty()) {
                Toast.makeText(applicationContext, "Isikan data", Toast.LENGTH_SHORT).show()
            } else {
               noteViewModel.insert(Note(null, title, description))
                Toast.makeText(applicationContext, "Sukses Menyimpan Data", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(AppDatabase.getInstance(applicationContext))
        val viewModelProvideFactory = NoteViewModelFactory(noteRepository)

        noteViewModel = ViewModelProvider(this, viewModelProvideFactory).get(NoteViewModel::class.java)
    }
}