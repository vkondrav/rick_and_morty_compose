package com.vkondrav.playground.app.screen.drawer.usecase

import com.vkondrav.playground.app.screen.characters.nav.charactersScreen
import com.vkondrav.playground.app.screen.drawer.domain.DrawerMenuItem

class DrawerMenuUseCase {
    operator fun invoke(): List<DrawerMenuItem> = listOf(
        DrawerMenuItem(
            title = "Characters",
            route = charactersScreen.route,
        )
    )
}