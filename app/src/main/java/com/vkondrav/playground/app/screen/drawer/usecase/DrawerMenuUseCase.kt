package com.vkondrav.playground.app.screen.drawer.usecase

import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.screen.characters.nav.charactersScreen
import com.vkondrav.playground.app.screen.drawer.composable.DrawerMenuViewItem

class DrawerMenuUseCase(
    private val appState: AppState,
) {
    operator fun invoke(): List<ComposableItem> = listOf(
        charactersScreen,
    ).map {
        DrawerMenuViewItem(
            screen = it,
            onClickAction = {
                appState.navigate(it.id)
                appState.closeDrawer()
            }
        )
    }
}