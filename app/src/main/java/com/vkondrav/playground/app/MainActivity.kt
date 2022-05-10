package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.navigation.defineGraph
import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.common.state.AppStateImpl
import com.vkondrav.playground.app.drawer.composable.Drawer
import com.vkondrav.playground.app.page1.nav.page1Screen
import com.vkondrav.playground.app.snackbar.SnackbarHost
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }
            val drawerState =
                rememberDrawerState(initialValue = DrawerValue.Closed)

            LoadAppStateIntoKoin(
                navController, snackbarHostState, drawerState,
            )
            MaterialTheme {
                Drawer(drawerState) {
                    Box {
                        Column {
                            CustomAppBar(navController)
                            NavHost(
                                navController = navController,
                                startDestination = page1Screen.id,
                            ) {
                                defineGraph()
                            }
                        }
                        SnackbarHost(snackbarHostState)
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadAppStateIntoKoin(
    navController: NavController,
    snackbarHostState: SnackbarHostState,
    drawerState: DrawerState,
) {
    val coroutineScope = rememberCoroutineScope()

    loadKoinModules(module {
        single<AppState> {
            AppStateImpl(
                navController = navController,
                snackbarHostState = snackbarHostState,
                drawerState = drawerState,
                coroutineScope = coroutineScope,
            )
        }
    })
}
