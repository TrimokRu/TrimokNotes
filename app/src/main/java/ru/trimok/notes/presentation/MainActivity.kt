package ru.trimok.notes.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ru.trimok.notes.data.repository.NoteRepositoryImpl
import ru.trimok.notes.databinding.ActivityMainBinding
import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.usecase.AddUserNoteUseCase
import ru.trimok.notes.domain.usecase.DeleteUserNoteUseCase
import ru.trimok.notes.domain.usecase.GetUserNotesUseCase
import ru.trimok.notes.presentation.adapters.NotesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val noteRepository by lazy { NoteRepositoryImpl(context = applicationContext) }
    private val addUserNoteUseCase by lazy { AddUserNoteUseCase(noteRepository = noteRepository) }
    private val getUserNotesUseCase by lazy { GetUserNotesUseCase(noteRepository = noteRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notesAdapter = NotesAdapter(applicationContext)
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter
        notesAdapter.addItems(getUserNotesUseCase.execute())

        if(notesAdapter.itemCount < 1) binding.notFoundTextView.visibility = View.VISIBLE


        binding.saveButton.setOnClickListener {
            addUserNoteUseCase.execute(Note(noteName = binding.nameNoteTextField.editText?.text.toString()))?.let { note -> notesAdapter.addItem(note) }
        if(notesAdapter.itemCount > 0) binding.notFoundTextView.visibility = View.INVISIBLE
        }
    }
}