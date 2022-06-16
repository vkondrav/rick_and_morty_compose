package com.vkondrav.playground.app.screen.characters.nav

import androidx.navigation.navArgument
import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.common.navigation.NAV_TITLE
import com.vkondrav.playground.app.screen.characters.composable.CharactersScreen
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.common.utils.TextResource

val charactersScreen = Screen(
    route = "characters?$NAV_TITLE={$NAV_TITLE}",
    arguments = listOf(
        navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.characters) },
    ),
) { CharactersScreen() }
