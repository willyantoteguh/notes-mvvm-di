package com.example.notes_mvvm_di.ui.addnote

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.notes_mvvm_di.data.model.Note
import com.example.notes_mvvm_di.databinding.ActivityAddBinding
import com.example.notes_mvvm_di.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity: AppCompatActivity() {
    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }
    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
}