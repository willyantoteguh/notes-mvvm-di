package com.example.notes_mvvm_di.ui.listnote

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
//import androidx.appcompat.widget.PopupMenu
//import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.notes_mvvm_di.R
import com.example.notes_mvvm_di.data.model.Note
import com.example.notes_mvvm_di.databinding.ItemNoteBinding
import com.example.notes_mvvm_di.listener.OnNoteClickListener

class ListNoteAdapter(val context:  Context, val onNoteClickListener: OnNoteClickListener): RecyclerView.Adapter<ListNoteAdapter.ViewHolder>() {
    private val notes = ArrayList<Note>()

    class ViewHolder(val binding: ItemNoteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount()= notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes.get(position)
        holder.binding.tvTitle.setText(note.title)
        holder.binding.tvDescription.setText(note.description)
        holder.binding.cvItem.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor()))

        holder.binding.cvItem.setOnClickListener {
            val popupMenu = PopupMenu(context, holder.binding.tvDescription)
            popupMenu.menuInflater.inflate(R.menu.menu_option, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.item_delete -> onNoteClickListener.onDelete(note)
                }
                true
            })
            popupMenu.show()
//            true
        }
    }

    fun updateList(newNotes: List<Note>) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    fun randomColor(): Int {
        val list = ArrayList<Int>()

        list.add(R.color.color_1)
        list.add(R.color.color_2)
        list.add(R.color.color_3)
        list.add(R.color.color_4)
        list.add(R.color.color_5)
        list.add(R.color.color_6)
        return  list.random()
    }
}