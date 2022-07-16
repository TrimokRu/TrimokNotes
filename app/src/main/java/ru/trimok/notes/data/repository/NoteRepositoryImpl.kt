package ru.trimok.notes.data.repository


import ru.trimok.notes.data.storage.models.NoteData
import ru.trimok.notes.data.storage.room.NoteStorage
import ru.trimok.notes.domain.models.Note
import ru.trimok.notes.domain.repository.NoteRepository

class NoteRepositoryImpl(private val noteStorage: NoteStorage): NoteRepository {


    override fun addNote(note: Note): Long{

        return noteStorage.addNote(NoteData(id = note.id, noteName = note.noteName))
    }

    override fun deleteNote(noteId: Long): Boolean{
        return noteStorage.deleteNote(noteId)
    }

    override fun getNotes(): MutableList<Note>{
        return mLNoteDataToMLNote(noteStorage.getNotes())
    }

    private fun mLNoteDataToMLNote(mutableList: MutableList<NoteData>): MutableList<Note>{
        val mutableListRes = mutableListOf<Note>()
        mutableList.map {
            mutableListRes.add(Note(id = it.id, noteName = it.noteName))
        }
        return mutableListRes
    }

}