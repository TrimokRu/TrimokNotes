package ru.trimok.notes.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.trimok.notes.presentation.MainViewModel


val presentationModule = module {
    viewModel {
        MainViewModel(getUserNotesUseCase = get(), addUserNoteUseCase = get(), deleteUserNoteUseCase = get())
    }
}