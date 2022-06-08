package com.vkondrav.playground.app.screen.favorite_characters.nav

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.favorite_characters.composable.FavoriteCharactersScreen
import com.vkondrav.playground.app.tabs.nav.TabScreen

val favoriteCharactersTab = TabScreen(
    title = TextResource.Resource(R.string.characters),
) { FavoriteCharactersScreen() }
