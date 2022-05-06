package com.vkondrav.playground.app.characters.di

import com.vkondrav.playground.app.characters.viewmodel.CharactersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    viewModel {
        CharactersViewModel(
            ramRepository = get(),
            dispatcher = get(),
        )
    }
}