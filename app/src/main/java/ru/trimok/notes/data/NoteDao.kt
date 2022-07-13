package ru.trimok.notes.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
     fun addNote(note: Note)

    @Query("SELECT * FROM note_table")
    fun getNotes(): MutableList<Note>
}