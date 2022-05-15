package com.vkondrav.playground.app.screen.character_details.nav

import android.os.Bundle
import com.vkondrav.playground.app.common.navigation.NavigationException
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsScreen

private const val CHARACTER_DETAILS = "character_details"
private const val ID = "id"
private const val TITLE = "title"

val characterDetailsScreen = Screen(
    route = "$CHARACTER_DETAILS/{$ID}?$TITLE={$TITLE}",
) { bundle ->
    CharacterDetailsScreen(
        id = bundle?.id
            ?: throw NavigationException("Navigating to character details screen with no id")
    )
}

val Bundle.id get() = this.getString(ID)
val Bundle.title get() = this.getString(TITLE)

fun toCharacterDetailsScreen(id: String, title: String) = "$CHARACTER_DETAILS/$id?$TITLE=$title"