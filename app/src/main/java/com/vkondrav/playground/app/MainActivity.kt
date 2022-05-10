package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.navigation.defineGraph
import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.common.state.AppStateImpl
import com.vkondrav.playground.app.drawer.composable.Drawer
import com.vkondrav.playground.app.page1.nav.page1Screen
import com.vkondrav.playground.app.snackbar.SnackbarHost
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {

            val bottomSheetNavigator = rememberBottomSheetNavigator()
            val navController =
                rememberAnimatedNavController(bottomSheetNavigator)
            val snackbarHostState = remember { SnackbarHostState() }
            val drawerState =
                rememberDrawerState(initialValue = DrawerValue.Closed)

            LoadAppStateIntoKoin(
                navController, snackbarHostState, drawerState,
            )
            MaterialTheme {
                Drawer(drawerState) {
                    ModalBottomSheetLayout(bottomSheetNavigator) {
                        Box {
                            Column {
                                CustomAppBar(navController)
                                AnimatedNavHost(
                                    navController = navController,
                                    startDestination = page1Screen.id,
                                ) {
                                    defineGraph()
                                }
                            }
                            Button(
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .padding(16.dp),
                                onClick = { navController.navigate("bottom_sheet") }) {
                                Text(text = "Bottom Sheet")
                            }
                            SnackbarHost(snackbarHostState)
                        }
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
