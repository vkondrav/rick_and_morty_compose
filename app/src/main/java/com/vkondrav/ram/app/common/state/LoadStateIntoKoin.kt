package com.vkondrav.ram.app.common.state

import androidx.navigation.NavController
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

fun loadStateIntoKoin(
    navController: NavController,
) {
    loadKoinModules(
        module {
            factory {
                navController
            }
        },
    )
}
