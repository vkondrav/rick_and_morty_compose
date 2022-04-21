package com.vkondrav.playground.app.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction
import com.vkondrav.playground.app.page1.nav.page1Screen
import com.vkondrav.playground.app.page2.nav.page2Screen
import com.vkondrav.playground.app.tabs.nav.tabsScreen

data class Screen(
    val id: String,
    val title: String,
    private val compose: @Composable () -> Unit,
) : ComposableItem {
    @Composable
    override fun Composable(action: OnComposableAction) = compose()
}

val allScreens = listOf(
    page1Screen,
    page2Screen,
    page2Screen,
    tabsScreen,
)

fun NavGraphBuilder.defineGraph() {
    allScreens.forEach { screen ->
        composable(screen.id) { screen.Composable(action = { }) }
    }
}