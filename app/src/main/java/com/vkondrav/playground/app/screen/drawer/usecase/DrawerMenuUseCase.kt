package com.vkondrav.playground.app.screen.drawer.usecase

import com.vkondrav.playground.app.screen.characters.nav.charactersScreen
import com.vkondrav.playground.app.screen.drawer.domain.DrawerMenuItem
import com.vkondrav.playground.app.screen.episodes.nav.episodesScreen
import com.vkondrav.playground.app.screen.favorite_characters.nav.favoriteCharactersScreen
import com.vkondrav.playground.app.screen.locations.nav.locationsScreen

class DrawerMenuUseCase {
    operator fun invoke(): List<DrawerMenuItem> = listOf(
        "Characters" to charactersScreen.route,
        "Locations" to locationsScreen.route,
        "Episodes" to  episodesScreen.route,
        "Favorite Character" to favoriteCharactersScreen.route,
    ).map {
        DrawerMenuItem(
            title = it.first,
            route = it.second,
        )
    }
}
