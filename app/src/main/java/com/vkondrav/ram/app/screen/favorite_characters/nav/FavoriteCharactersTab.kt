package com.vkondrav.ram.app.screen.favorite_characters.nav

import com.vkondrav.ram.app.R
import com.vkondrav.ram.util.TextResource
import com.vkondrav.ram.app.screen.favorite_characters.composable.FavoriteCharactersScreen
import com.vkondrav.ram.app.tabs.nav.TabScreen

val favoriteCharactersTab = TabScreen(
    title = TextResource.Resource(R.string.characters),
) { FavoriteCharactersScreen() }
