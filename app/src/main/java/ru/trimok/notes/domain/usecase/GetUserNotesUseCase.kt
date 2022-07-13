package ru.trimok.notes.domain.usecase

import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.repository.NoteRepository

class GetUserNotesUseCase(private val noteRepository: NoteRepository) {

    fun execute(): MutableList<Note> {
        return noteRepository.getNotes()
    }
}