package ru.trimok.notes.presentation

import android.os.Bundle
import android.util.Log
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
    private val deleteUserNoteUseCase by lazy { DeleteUserNoteUseCase(noteRepository = noteRepository) }
    private val getUserNotesUseCase by lazy { GetUserNotesUseCase(noteRepository = noteRepository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notesAdapter = NotesAdapter()
        binding.notesRecyclerView.layoutManager= LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter
        notesAdapter.addItems(getUserNotesUseCase.execute())


        binding.saveButton.setOnClickListener {
            val note = Note(binding.nameNoteTextField.editText?.text.toString())
            if(addUserNoteUseCase.execute(note = note)){
                notesAdapter.addItem(note)
                binding.nameNoteTextField.editText?.setText("")
            }
        }
    }
}