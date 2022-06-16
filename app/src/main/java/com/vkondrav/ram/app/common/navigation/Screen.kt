package com.vkondrav.ram.app.common.navigation

import android.os.Bundle
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.vkondrav.ram.app.screen.character_details.nav.characterDetailsScreen
import com.vkondrav.ram.app.screen.characters.nav.charactersScreen
import com.vkondrav.ram.app.screen.episode_details.nav.episodeDetailsScreen
import com.vkondrav.ram.app.screen.episodes.nav.episodesScreen
import com.vkondrav.ram.app.screen.location_details.nav.locationDetailsScreen
import com.vkondrav.ram.app.screen.locations.nav.locationsScreen

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
                fadeIn(
                    animationSpec = spring(stiffness = Spring.StiffnessMedium),
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = spring(stiffness = Spring.StiffnessMedium),
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
