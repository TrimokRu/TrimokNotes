package ru.trimok.notes.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.trimok.notes.R
import ru.trimok.notes.databinding.ItemNoteBinding
import ru.trimok.notes.domain.models.Note

class NotesAdapter():  RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notesMutableList = mutableListOf<Note>()

    class NotesViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemNoteBinding.bind(item)

        fun bind(note: Note) = with(binding){
            itemTitleNoteTextView.text = note.noteName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(notesMutableList[position])
    }

    override fun getItemCount(): Int {
        return notesMutableList.size
    }

    fun addItem(note: Note){
        notesMutableList.add(note)
        notifyItemChanged(notesMutableList.size - 1)
    }

    fun addItems(notesMutableList: MutableList<Note>){
        val rangeMin = this.notesMutableList.size
        val rangeMax = notesMutableList.size
        this.notesMutableList.addAll(notesMutableList)
        notifyItemRangeChanged(rangeMin, rangeMin + rangeMax - 1)
    }
}