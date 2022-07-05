package com.vkondrav.ram.navigation

import android.os.Bundle
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable

data class Screen(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList(),
    val compose: @Composable (Bundle?) -> Unit,
)

fun NavGraphBuilder.defineGraph(screens: List<Screen>) {
    screens.forEach { screen ->
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
