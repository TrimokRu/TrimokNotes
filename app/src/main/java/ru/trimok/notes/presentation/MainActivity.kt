package ru.trimok.notes.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.trimok.notes.data.repository.NoteRepositoryImpl
import ru.trimok.notes.databinding.ActivityMainBinding
import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.usecase.AddUserNoteUseCase
import ru.trimok.notes.domain.usecase.DeleteUserNoteUseCase
import ru.trimok.notes.domain.usecase.GetUserNotesUseCase

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

        binding.saveButton.setOnClickListener {
            Snackbar.make(
                binding.root,
                addUserNoteUseCase.execute(note = Note(binding.nameNoteTextField.editText?.text.toString())),
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}