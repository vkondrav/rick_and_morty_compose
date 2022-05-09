package com.vkondrav.playground.app.characters.di

import com.vkondrav.playground.app.characters.viewmodel.CharactersViewModel
import com.vkondrav.playground.app.page1.source.CharacterSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val charactersModule = module {
    factory {
        CharacterSource(
            ramRepository = get(),
        )
    }
    viewModel {
        CharactersViewModel(
            characterSource = get(),
            dispatcher = get(),
        )
    }
}