package ru.trimok.notes.di

import org.koin.dsl.module
import ru.trimok.notes.domain.usecase.AddUserNoteUseCase
import ru.trimok.notes.domain.usecase.DeleteUserNoteUseCase
import ru.trimok.notes.domain.usecase.GetUserNotesUseCase


val domainModule = module{
    factory {
        AddUserNoteUseCase(noteRepository = get())
    }
    factory {
        GetUserNotesUseCase(noteRepository = get())
    }
    factory {
        DeleteUserNoteUseCase(noteRepository = get())
    }
}