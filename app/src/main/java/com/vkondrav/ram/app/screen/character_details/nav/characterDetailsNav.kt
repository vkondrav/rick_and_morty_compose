package com.vkondrav.ram.app.screen.character_details.nav

import com.vkondrav.ram.app.common.navigation.NAV_ID
import com.vkondrav.ram.app.common.navigation.NAV_TITLE
import com.vkondrav.ram.app.common.navigation.id
import com.vkondrav.ram.app.common.navigation.NavigationException
import com.vkondrav.ram.app.common.navigation.Screen
import com.vkondrav.ram.app.screen.character_details.composable.CharacterDetailsScreen

private const val NAV_CHARACTER_DETAILS = "character_details"

val characterDetailsScreen = Screen(
    route = "$NAV_CHARACTER_DETAILS/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}",
) { bundle ->
    CharacterDetailsScreen(
        id = bundle?.id
            ?: throw NavigationException("Navigating to character details screen with no id"),
    )
}

fun toCharacterDetailsScreen(id: String, title: String) =
    "$NAV_CHARACTER_DETAILS/$id?$NAV_TITLE=$title"
