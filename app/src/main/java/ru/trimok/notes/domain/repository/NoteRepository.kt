package ru.trimok.notes.domain.repository

import ru.trimok.notes.domain.models.Note

interface NoteRepository {

    fun addNote(note: Note): Long

    fun deleteNote(noteId: Long): Boolean

    fun getNotes(): MutableList<Note>
}