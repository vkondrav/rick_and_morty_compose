package com.vkondrav.ram.character.all.nav

import androidx.navigation.navArgument
import com.vkondrav.ram.character.all.R
import com.vkondrav.ram.character.all.composable.CharactersScreen
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.navigation.NAV_TITLE
import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.data.Screen

val charactersScreen by lazy {
    Screen(
        route = Routes.Character.All(),
        arguments = listOf(
            navArgument(NAV_TITLE) { defaultValue = TextResource.Resource(R.string.characters) },
        ),
    ) { CharactersScreen() }
}
