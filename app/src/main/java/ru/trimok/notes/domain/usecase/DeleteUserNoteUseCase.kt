package ru.trimok.notes.domain.usecase

import ru.trimok.notes.domain.repository.NoteRepository

class DeleteUserNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(noteId: Int): Boolean{
        return noteRepository.deleteNote(noteId)
    }
}