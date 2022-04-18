package com.vkondrav.playground.app.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vkondrav.playground.app.page1.composable.Page1Screen
import com.vkondrav.playground.app.page2.composable.Page2Screen
import com.vkondrav.playground.app.page3.composable.Page3Screen

object Route {
    const val Screen1 = "screen1"
    const val Screen2 = "screen2"
    const val Screen3 = "screen3"
}

fun NavGraphBuilder.basicGraph(navController: NavController) {
    composable(Route.Screen1) { Page1Screen(navController) }
    composable(Route.Screen2) { Page2Screen(navController) }
    composable(Route.Screen3) { Page3Screen(navController) }
}