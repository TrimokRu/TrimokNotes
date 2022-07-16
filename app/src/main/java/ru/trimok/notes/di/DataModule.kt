package ru.trimok.notes.di

import org.koin.dsl.module
import ru.trimok.notes.data.repository.NoteRepositoryImpl
import ru.trimok.notes.data.storage.room.NoteStorage
import ru.trimok.notes.data.storage.room.RoomNoteStorage
import ru.trimok.notes.domain.repository.NoteRepository


val dataModule = module{
    single<NoteStorage>{
        RoomNoteStorage(context = get())
    }

    single<NoteRepository>{
        NoteRepositoryImpl(noteStorage = get())
    }
}