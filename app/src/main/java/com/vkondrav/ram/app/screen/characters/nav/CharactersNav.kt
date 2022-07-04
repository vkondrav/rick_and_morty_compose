package com.vkondrav.ram.app.screen.characters.nav

import androidx.navigation.navArgument
import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.common.navigation.NAV_TITLE
import com.vkondrav.ram.app.screen.characters.composable.CharactersScreen
import com.vkondrav.ram.app.common.navigation.Screen
import com.vkondrav.ram.util.TextResource

val charactersScreen = Screen(
    route = "characters?$NAV_TITLE={$NAV_TITLE}",
    arguments = listOf(
        navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.characters) },
    ),
) { CharactersScreen() }
