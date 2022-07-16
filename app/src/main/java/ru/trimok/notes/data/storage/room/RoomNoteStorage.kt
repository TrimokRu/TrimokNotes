package ru.trimok.notes.data.storage.room

import android.content.Context
import android.util.Log
import ru.trimok.notes.data.room.NoteDatabase
import ru.trimok.notes.data.storage.models.NoteData
import java.lang.Exception

class RoomNoteStorage(context: Context): NoteStorage {

    private val noteDatabase = NoteDatabase.getDatabase(context)

    override fun addNote(note: NoteData): Long{
        return try {
            noteDatabase.noteDao().addNote(ru.trimok.notes.data.room.Note(noteName = note.noteName))
        }catch (e: Exception){
            Log.e("Room", e.toString())
            -1
        }

    }

    override fun deleteNote(noteId: Long): Boolean{
        return noteDatabase.noteDao().deleteItem(noteId) == 1
    }

    override fun getNotes(): MutableList<NoteData>{
        val notesData = mutableListOf<ru.trimok.notes.data.room.Note>()
        val notesResult = mutableListOf<NoteData>()
        try{
            notesData.addAll(noteDatabase.noteDao().getNotes())
            if(notesData.size > 0){
                for (i in notesData)
                    notesResult.add(NoteData(id = i.id, noteName = i.noteName))
            }
        }catch (e: Exception){
            Log.e("room", e.toString())
        }
        return notesResult
    }
}