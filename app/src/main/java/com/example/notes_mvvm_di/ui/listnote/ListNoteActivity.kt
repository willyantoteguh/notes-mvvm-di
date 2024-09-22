package com.example.notes_mvvm_di.ui.listnote

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes_mvvm_di.AppDatabase
import com.example.notes_mvvm_di.R
import com.example.notes_mvvm_di.data.model.Note
import com.example.notes_mvvm_di.databinding.ActivityListBinding
import com.example.notes_mvvm_di.listener.OnNoteClickListener
import com.example.notes_mvvm_di.repository.NoteRepository
import com.example.notes_mvvm_di.ui.addnote.AddNoteActivity
import com.example.notes_mvvm_di.viewmodel.NoteViewModel
import com.example.notes_mvvm_di.viewmodel.NoteViewModelFactory

class ListNoteActivity : AppCompatActivity() {
    private val binding by lazy { ActivityListBinding.inflate(layoutInflater) }
    private lateinit var noteViewModel: NoteViewModel
    private lateinit var adapter: ListNoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupViewModel()

        adapter = ListNoteAdapter(applicationContext, object: OnNoteClickListener {
            override fun onDelete(note: Note) {
                TODO("Not yet implemented")
            }

        })

        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayout.VERTICAL)
        binding.recyclerView.adapter = adapter

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(applicationContext, AddNoteActivity::class.java)
            startActivity(intent)
        }

        noteViewModel.getAll().observe(this) { listNote ->
            listNote.let {
                adapter.updateList(it)
            }
        }
    }

    private fun setupViewModel() {
        val noteRepository = NoteRepository(AppDatabase.getInstance(applicationContext))
        val viewModelProvideFactory = NoteViewModelFactory(noteRepository)

        noteViewModel = ViewModelProvider(this, viewModelProvideFactory).get(NoteViewModel::class.java)
    }
}