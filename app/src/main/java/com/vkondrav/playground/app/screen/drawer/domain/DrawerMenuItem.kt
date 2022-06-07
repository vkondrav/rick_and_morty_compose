package com.vkondrav.playground.app.screen.drawer.domain

import com.vkondrav.playground.app.common.utils.TextResource

data class DrawerMenuItem(
    val title: TextResource,
    val route: String,
)
