package com.vkondrav.playground.app.characters.nav

import com.vkondrav.playground.app.characters.composable.CharactersScreen
import com.vkondrav.playground.app.common.navigation.Screen

val charactersScreen = Screen(
    id = "characters",
    title = "Characters"
) { CharactersScreen() }