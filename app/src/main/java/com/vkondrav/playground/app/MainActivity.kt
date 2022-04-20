package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.di.loadIntoKoin
import com.vkondrav.playground.app.common.navigation.Route
import com.vkondrav.playground.app.common.navigation.basicGraph
import com.vkondrav.playground.app.drawer.composable.Drawer
import com.vkondrav.playground.app.snackbar.SnackbarHost

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController().also {
                it.loadIntoKoin()
            }
            val snackbarHostState = remember { SnackbarHostState() }.also {
                it.loadIntoKoin()
            }

            MaterialTheme {
                Drawer {
                    Box {
                        Column {
                            CustomAppBar()
                            NavHost(
                                navController = navController,
                                startDestination = Route.Screen1.id
                            ) {
                                basicGraph()
                            }
                        }
                        SnackbarHost(
                            snackbarHostState = snackbarHostState
                        )
                    }
                }
            }
        }
    }
}
