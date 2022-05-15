package com.vkondrav.playground.app.screen.characters.nav

import com.vkondrav.playground.app.screen.characters.composable.CharactersScreen
import com.vkondrav.playground.app.common.navigation.Screen

val charactersScreen = Screen(
    id = "characters",
    title = "Characters",
) { CharactersScreen() }