package com.vkondrav.playground.app.common.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.vkondrav.playground.app.page1.composable.Page1Screen
import com.vkondrav.playground.app.page2.composable.Page2Screen
import com.vkondrav.playground.app.page3.composable.Page3Screen
import com.vkondrav.playground.app.tabs.composable.TabsScreen

data class Screen(
    val id: String,
    val title: String,
    val compose: @Composable () -> Unit,
)

object Route {
    val Screen1 = Screen("screen1", "Screen 1") { Page1Screen() }
    val Screen2 = Screen("screen2", "Screen 2") { Page2Screen() }
    val Screen3 = Screen("screen3", "Screen 3") { Page3Screen() }
    val TabsScreen = Screen("tabs", "Tabs") { TabsScreen() }

    val allScreens = listOf(Screen1, Screen2, Screen3, TabsScreen)
}

fun NavGraphBuilder.basicGraph() {
    Route.allScreens.forEach { screen ->
        composable(screen.id) { screen.compose() }
    }
}