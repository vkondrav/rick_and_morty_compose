package com.vkondrav.ram.app.screen.locations.usecase

import androidx.navigation.NavController
import com.vkondrav.ram.app.screen.location_details.nav.toLocationDetailsScreen

class NavigateToLocationDetailsUseCase(
    private val navController: NavController,
) {
    operator fun invoke(id: String, title: String) {
        navController.navigate(
            toLocationDetailsScreen(
                id,
                title,
            ),
        )
    }
}
