package com.vkondrav.playground.app.common.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.OnComposableAction
import com.vkondrav.playground.app.characters.nav.charactersScreen
import com.vkondrav.playground.app.page1.nav.page1Screen
import com.vkondrav.playground.app.page2.nav.page2Screen
import com.vkondrav.playground.app.page3.nav.page3Screen
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
    page3Screen,
    tabsScreen,
    charactersScreen,
)

fun NavGraphBuilder.defineGraph() {
    allScreens.forEach { screen ->
        composable(
            screen.id,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Right,
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                fadeIn(
                    animationSpec = spring(stiffness = Spring.StiffnessMedium),
                )
            },
        ) { screen.Composable(action = { }) }
    }
}