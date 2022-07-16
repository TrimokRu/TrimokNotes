package ru.trimok.notes.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
     fun addNote(note: Note): Long

    @Query("SELECT * FROM note_table")
    fun getNotes(): MutableList<Note>

    @Query("DELETE FROM note_table WHERE id = :id")
    fun deleteItem(id: Long) : Int
}