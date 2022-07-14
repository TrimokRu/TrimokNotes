package ru.trimok.notes.domain.usecase

import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.repository.NoteRepository

class AddUserNoteUseCase(private val noteRepository: NoteRepository) {

    fun execute(note: Note): Note?{
        val noteId = noteRepository.addNote(note)
        return if(noteId > -1)  Note(noteName = note.noteName, id = noteId)
        else null
    }
}