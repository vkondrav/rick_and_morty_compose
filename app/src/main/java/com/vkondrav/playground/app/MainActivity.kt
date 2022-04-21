package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.navigation.defineGraph
import com.vkondrav.playground.app.core.composableScopeQualifier
import com.vkondrav.playground.app.drawer.composable.Drawer
import com.vkondrav.playground.app.page1.nav.page1Screen
import com.vkondrav.playground.app.snackbar.SnackbarHost
import org.koin.android.ext.android.get
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            LoadStatesIntoKoin()
            MaterialTheme {
                Drawer(
                    drawerState = get(),
                ) {
                    Box {
                        Column {
                            CustomAppBar(
                                navController = get(),
                            )
                            NavHost(
                                navController = get(),
                                startDestination = page1Screen.id,
                            ) {
                                defineGraph()
                            }
                        }
                        SnackbarHost(
                            snackbarHostState = get(),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun LoadStatesIntoKoin() {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val composableScope = rememberCoroutineScope()

    loadKoinModules(module {
        single<NavHostController> {
            navController
        }
        single<NavController> {
            navController
        }
        single {
            snackbarHostState
        }
        single {
            drawerState
        }
        single(qualifier = composableScopeQualifier) {
            composableScope
        }
    })
}
