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
import com.vkondrav.playground.app.screen.episode_details.nav.episodeDetailsScreen
import com.vkondrav.playground.app.screen.episodes.nav.episodesScreen
import com.vkondrav.playground.app.screen.location_details.nav.locationDetailsScreen
import com.vkondrav.playground.app.screen.locations.nav.locationsScreen

data class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val compose: @Composable (Bundle?) -> Unit,
)

val allScreens = listOf(
    charactersScreen,
    characterDetailsScreen,
    locationsScreen,
    locationDetailsScreen,
    episodesScreen,
    episodeDetailsScreen,
)

fun NavGraphBuilder.defineGraph() {
    allScreens.forEach { screen ->
        composable(
            route = screen.route,
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