package com.vkondrav.playground.app.common.navigation

import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.vkondrav.playground.app.screen.character_details.nav.characterDetailsScreen
import com.vkondrav.playground.app.screen.characters.nav.charactersScreen

data class Screen(
    val id: String,
    val title: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val compose: @Composable (Bundle?) -> Unit,
)

val allScreens = listOf(
    charactersScreen,
    characterDetailsScreen,
)

fun NavGraphBuilder.defineGraph() {
    allScreens.forEach { screen ->
        composable(
            route = screen.id,
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
            arguments = screen.arguments,
        ) {
            screen.compose(it.arguments)
        }
    }
}