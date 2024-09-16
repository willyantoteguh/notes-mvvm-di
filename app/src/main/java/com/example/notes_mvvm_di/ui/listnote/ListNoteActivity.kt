package com.example.notes_mvvm_di.ui.listnote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notes_mvvm_di.R
import com.example.notes_mvvm_di.databinding.ActivityListBinding
import com.example.notes_mvvm_di.ui.addnote.AddNoteActivity

class ListNoteActivity : AppCompatActivity() {
    private val binding by lazy { ActivityListBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(applicationContext, AddNoteActivity::class.java)
            startActivity(intent)
        }
    }
}