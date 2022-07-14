package ru.trimok.notes.data.repository

import android.content.Context
import android.util.Log
import ru.trimok.notes.data.NoteDatabase
import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.repository.NoteRepository
import java.lang.Exception

class NoteRepositoryImpl(context: Context): NoteRepository {


    private val noteDatabase = NoteDatabase.getDatabase(context)

    override fun addNote(note: Note): Long{
        return try {
            noteDatabase.noteDao().addNote(ru.trimok.notes.data.Note(noteName = note.noteName))
        }catch (e: Exception){
            Log.e("Room", e.toString())
            -1
        }

    }

    override fun deleteNote(noteId: Long): Boolean{
        return noteDatabase.noteDao().deleteItem(noteId) == 1
    }

    override fun getNotes(): MutableList<Note>{
        val notesData = mutableListOf<ru.trimok.notes.data.Note>()
        val notesResult = mutableListOf<Note>()
        try{
            notesData.addAll(noteDatabase.noteDao().getNotes())
            if(notesData.size > 0){
                for (i in notesData)
                    notesResult.add(Note(id = i.id, noteName = i.noteName))
            }
        }catch (e: Exception){
            Log.e("room", e.toString())
        }
        return notesResult
    }
}