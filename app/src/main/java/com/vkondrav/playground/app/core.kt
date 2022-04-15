package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraphBuilder
import com.vkondrav.playground.app.about.AboutScreen
import com.vkondrav.playground.app.global.HostScreen
import com.vkondrav.playground.app.home.HomeScreen
import com.vkondrav.playground.app.ui.animation.nav.enterAnimation
import com.vkondrav.playground.app.ui.animation.nav.exitAnimation
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

// -- Routes & Navigation ------------------------------------------------------

object Routes {
    const val home = "/home"
    const val about = "/about"
}

@Suppress("EnumEntryName")
enum class Keys {
    // Events
    update_screen_title,
    // Subs
    sdk_version,
}

@ExperimentalAnimationApi
fun NavGraphBuilder.homeComposable(animOffSetX: Int) {
    composable(
        route = Routes.home,
        exitTransition = { exitAnimation(targetOffsetX = -animOffSetX) },
        popEnterTransition = { enterAnimation(initialOffsetX = -animOffSetX) }
    ) {
        HomeScreen()
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.aboutComposable(animOffSetX: Int) {
    composable(
        route = Routes.about,
        enterTransition = { enterAnimation(initialOffsetX = animOffSetX) },
        popExitTransition = { exitAnimation(targetOffsetX = animOffSetX) },
    ) {
        AboutScreen()
    }
}

@ExperimentalAnimationApi
@Composable
fun Navigation(padding: PaddingValues) {
    val navController = rememberAnimatedNavController()
    LaunchedEffect(navController) {
    }

    AnimatedNavHost(
        modifier = Modifier.padding(padding),
        navController = navController,
        startDestination = Routes.home
    ) {
        homeComposable(animOffSetX = 300)
        aboutComposable(animOffSetX = 300)
    }
}

// -- Entry Point --------------------------------------------------------------

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen = installSplashScreen()

        setContent {
            HostScreen {
                Navigation(padding = it)
            }
        }
    }
}
