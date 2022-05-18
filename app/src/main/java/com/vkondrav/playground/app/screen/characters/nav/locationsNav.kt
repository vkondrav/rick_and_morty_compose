package com.vkondrav.playground.app.screen.characters.nav

import androidx.navigation.navArgument
import com.vkondrav.playground.app.screen.characters.composable.CharactersScreen
import com.vkondrav.playground.app.common.navigation.Screen

val charactersScreen = Screen(
    route = "characters?title={title}",
    arguments = listOf(
        navArgument("title") { defaultValue = "Characters" }
    ),
) { CharactersScreen() }