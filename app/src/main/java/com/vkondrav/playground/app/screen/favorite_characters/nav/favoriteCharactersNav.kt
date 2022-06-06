package com.vkondrav.playground.app.screen.favorite_characters.nav

import androidx.navigation.navArgument
import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.screen.favorite_characters.composable.FavoriteCharactersScreen

val favoriteCharactersScreen = Screen(
    route = "favorite_characters?title={title}",
    arguments = listOf(
        navArgument("title") { defaultValue = "Favorite Characters" },
    ),
) { FavoriteCharactersScreen() }
