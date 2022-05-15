package com.vkondrav.playground.app.tabs.nav

import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.tabs.composable.TabsScreen

val tabsScreen = Screen(
    route = "tabs",
) { TabsScreen() }