package ru.trimok.notes.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.trimok.notes.data.repository.NoteRepositoryImpl
import ru.trimok.notes.data.storage.room.RoomNoteStorage
import ru.trimok.notes.domain.usecase.AddUserNoteUseCase
import ru.trimok.notes.domain.usecase.DeleteUserNoteUseCase
import ru.trimok.notes.domain.usecase.GetUserNotesUseCase

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    private val noteRepository by lazy(LazyThreadSafetyMode.NONE) {
        NoteRepositoryImpl(
            noteStorage = RoomNoteStorage(
                context = context
            )
        )
    }
    private val addUserNoteUseCase by lazy(LazyThreadSafetyMode.NONE) {
        AddUserNoteUseCase(
            noteRepository = noteRepository
        )
    }
    private val getUserNotesUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNotesUseCase(
            noteRepository = noteRepository
        )
    }
    private val deleteUserNoteUseCase by lazy { DeleteUserNoteUseCase(noteRepository = noteRepository) }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            addUserNoteUseCase = addUserNoteUseCase,
            getUserNotesUseCase = getUserNotesUseCase,
            deleteUserNoteUseCase = deleteUserNoteUseCase
        ) as T
    }
}