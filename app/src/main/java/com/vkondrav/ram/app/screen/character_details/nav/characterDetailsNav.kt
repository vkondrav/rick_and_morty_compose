package com.vkondrav.ram.app.screen.character_details.nav

import com.vkondrav.ram.app.screen.character_details.composable.CharacterDetailsScreen
import com.vkondrav.ram.navigation.Routes
import com.vkondrav.ram.navigation.data.Screen
import com.vkondrav.ram.navigation.error.NavigationException
import com.vkondrav.ram.navigation.id

val characterDetailsScreen by lazy {
    Screen(
        route = Routes.Character.Details(),
    ) { bundle ->
        CharacterDetailsScreen(
            id = bundle?.id
                ?: throw NavigationException("Navigating to character details screen with no id"),
        )
    }
}
