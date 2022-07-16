package ru.trimok.notes.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.trimok.notes.R
import ru.trimok.notes.data.repository.NoteRepositoryImpl
import ru.trimok.notes.data.storage.room.RoomNoteStorage
import ru.trimok.notes.databinding.ActivityMainBinding
import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.usecase.AddUserNoteUseCase
import ru.trimok.notes.domain.usecase.DeleteUserNoteUseCase
import ru.trimok.notes.domain.usecase.GetUserNotesUseCase
import ru.trimok.notes.presentation.adapters.NotesAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getNoteAdapterSize().observe(this) { size ->
            binding.noteSizeTextView.text = getString(R.string.notes_size).format(size)
            if (size > 0) binding.notFoundTextView.visibility = View.GONE
            else binding.notFoundTextView.visibility = View.VISIBLE
        }

        val notesAdapter = NotesAdapter(mainViewModel = mainViewModel)
        binding.notesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.notesRecyclerView.adapter = notesAdapter
        notesAdapter.addItems(mainViewModel.getNotes())

        binding.saveButton.setOnClickListener {
            mainViewModel.addNote(Note(noteName = binding.nameNoteTextField.editText?.text.toString()))?.let {
                    note -> notesAdapter.addItem(note)
                binding.nameNoteTextField.editText?.setText("")
            }
        }
    }
}