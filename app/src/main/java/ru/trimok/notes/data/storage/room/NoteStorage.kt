package ru.trimok.notes.data.storage.room

import ru.trimok.notes.data.storage.models.NoteData

interface NoteStorage {

    fun addNote(note: NoteData): Long

    fun deleteNote(noteId: Long): Boolean

    fun getNotes(): MutableList<NoteData>
}