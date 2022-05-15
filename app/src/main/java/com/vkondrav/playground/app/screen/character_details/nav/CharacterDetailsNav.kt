package com.vkondrav.playground.app.screen.character_details.nav

import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.vkondrav.playground.app.common.navigation.NavigationException
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsScreen

private const val CHARACTER_DETAILS = "character_details"
private const val ID = "id"

val characterDetailsScreen = Screen(
    id = "$CHARACTER_DETAILS/{$ID}",
    title = "Characters Details",
    arguments = listOf(
        navArgument(ID) { type = NavType.StringType },
    ),
) { bundle ->
    CharacterDetailsScreen(
        id = bundle?.getString(ID)
            ?: throw NavigationException("Navigating to character details screen with no id")
    )
}

fun routeToCharacterDetailsScreen(id: String) = "$CHARACTER_DETAILS/$id"