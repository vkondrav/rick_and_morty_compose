package com.vkondrav.playground.app.screen.character_details.nav

import com.vkondrav.playground.app.common.nav.NAV_ID
import com.vkondrav.playground.app.common.nav.NAV_TITLE
import com.vkondrav.playground.app.common.nav.id
import com.vkondrav.playground.app.common.navigation.NavigationException
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsScreen

private const val NAV_CHARACTER_DETAILS = "character_details"

val characterDetailsScreen = Screen(
    route = "$NAV_CHARACTER_DETAILS/{$NAV_ID}?$NAV_TITLE={$NAV_TITLE}",
) { bundle ->
    CharacterDetailsScreen(
        id = bundle?.id
            ?: throw NavigationException("Navigating to character details screen with no id")
    )
}

fun toCharacterDetailsScreen(id: String, title: String) = "$NAV_CHARACTER_DETAILS/$id?$NAV_TITLE=$title"