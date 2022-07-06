@file:JvmName("Utils")

package com.vkondrav.ram.navigation

import android.os.Bundle
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.vkondrav.ram.common.ui.data.TextResource
import com.vkondrav.ram.navigation.data.Screen

const val NAV_ID = "id"
const val NAV_TITLE = "title"

val Bundle.id: String? get() = getString(NAV_ID)
val Bundle?.title: TextResource
    get() = this?.getString(NAV_TITLE)?.let {
        TextResource.Literal(it)
    } ?: this?.getParcelable(NAV_TITLE)
    ?: TextResource.Literal("")

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




