package com.vkondrav.ram.character.favorite.nav

import com.vkondrav.ram.character.all.R
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.character.favorite.composable.FavoriteCharactersScreen
import com.vkondrav.ram.common.ui.data.TabScreen

val favoriteCharactersTab = TabScreen(
    title = TextResource.Resource(R.string.characters),
) { FavoriteCharactersScreen() }
