package ru.trimok.notes.domain.repository

import ru.trimok.notes.domain.models.Note

interface NoteRepository {

    fun addNote(note: Note): Boolean

    fun deleteNote(noteId: Int): Boolean

    fun getNotes(): MutableList<Note>
}