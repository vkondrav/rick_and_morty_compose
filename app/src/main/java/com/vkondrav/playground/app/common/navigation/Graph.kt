package com.vkondrav.playground.app.common.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vkondrav.playground.app.page1.composable.Page1Screen
import com.vkondrav.playground.app.page2.composable.Page2Screen
import com.vkondrav.playground.app.page3.composable.Page3Screen


data class Screen(
    val id: String,
    val title: String,
)

object Route {
    val Screen1 = Screen("screen1", "Screen 1")
    val Screen2 = Screen("screen2", "Screen 2")
    val Screen3 = Screen("screen3", "Screen 3")
}

fun NavGraphBuilder.basicGraph(navController: NavController) {
    composable(Route.Screen1.id) { Page1Screen(navController) }
    composable(Route.Screen2.id) { Page2Screen(navController) }
    composable(Route.Screen3.id) { Page3Screen(navController) }
}