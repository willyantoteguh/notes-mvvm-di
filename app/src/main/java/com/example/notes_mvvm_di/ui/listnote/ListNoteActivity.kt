package com.example.notes_mvvm_di.ui.listnote

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes_mvvm_di.data.model.Note
import com.example.notes_mvvm_di.databinding.ActivityListBinding
import com.example.notes_mvvm_di.listener.OnNoteClickListener
import com.example.notes_mvvm_di.ui.addnote.AddNoteActivity
import com.example.notes_mvvm_di.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListNoteActivity : AppCompatActivity() {
    private val binding by lazy { ActivityListBinding.inflate(layoutInflater) }
    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var adapter: ListNoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = ListNoteAdapter(applicationContext, object: OnNoteClickListener {
            override fun onDelete(note: Note) {
                noteViewModel.delete(note)
                Toast.makeText(applicationContext, note.title, Toast.LENGTH_SHORT).show()
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
}