package com.vkondrav.playground.app.page1.nav

import com.vkondrav.playground.app.common.navigation.Screen
import com.vkondrav.playground.app.page1.composable.Page1Screen

val page1Screen = Screen(
    id = "screen1",
    title = "Screen 1"
) { Page1Screen() }
