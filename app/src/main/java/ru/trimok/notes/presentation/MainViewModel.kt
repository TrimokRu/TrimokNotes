package ru.trimok.notes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.usecase.AddUserNoteUseCase
import ru.trimok.notes.domain.usecase.DeleteUserNoteUseCase
import ru.trimok.notes.domain.usecase.GetUserNotesUseCase

class MainViewModel(private val addUserNoteUseCase: AddUserNoteUseCase, private val getUserNotesUseCase: GetUserNotesUseCase, private val deleteUserNoteUseCase: DeleteUserNoteUseCase): ViewModel() {

    private val noteAdapterSize = MutableLiveData<Int>()
    fun getNoteAdapterSize(): LiveData<Int> = noteAdapterSize

    fun getNotes(): MutableList<Note>{
        val res = getUserNotesUseCase.execute()
        noteAdapterSize.value = res.size
        return res
    }

    fun addNote(note: Note): Note?{
        val res = addUserNoteUseCase.execute(note = note)
        res?.let { noteAdapterSize.value = noteAdapterSize.value?.plus(1) }
        return res
    }

    fun deleteNote(noteId: Long): Boolean{
        val res = deleteUserNoteUseCase.execute(noteId = noteId)
        if(res) noteAdapterSize.value = noteAdapterSize.value?.minus(1)
        return res
    }


}