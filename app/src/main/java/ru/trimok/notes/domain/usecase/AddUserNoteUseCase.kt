package ru.trimok.notes.domain.usecase

import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.repository.NoteRepository

class AddUserNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(note: Note): Boolean{
        return noteRepository.addNote(note)
    }
}