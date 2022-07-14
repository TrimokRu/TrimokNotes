package ru.trimok.notes.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.trimok.notes.R
import ru.trimok.notes.data.repository.NoteRepositoryImpl
import ru.trimok.notes.databinding.ItemNoteBinding
import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.usecase.AddUserNoteUseCase
import ru.trimok.notes.domain.usecase.DeleteUserNoteUseCase

class NotesAdapter(context: Context):  RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private val notesMutableList = mutableListOf<Note>()

    private val noteRepository by lazy { NoteRepositoryImpl(context = context) }
    private val deleteUserNoteUseCase by lazy { DeleteUserNoteUseCase(noteRepository = noteRepository) }

    inner class NotesViewHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = ItemNoteBinding.bind(item)

        fun bind(note: Note) = with(binding){
            itemTitleNoteTextView.text = note.noteName
            itemDeleteButton.setOnClickListener {
                if(deleteUserNoteUseCase.execute(noteId = note.id))
                    deleteItem(noteId = note.id)
            }
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

    fun deleteItem(noteId: Long){
        (notesMutableList.firstOrNull { it.id == noteId }).let {
            val notePos = notesMutableList.indexOf(it)
            notesMutableList.remove(it)
            notifyItemRemoved(notePos)
        }
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