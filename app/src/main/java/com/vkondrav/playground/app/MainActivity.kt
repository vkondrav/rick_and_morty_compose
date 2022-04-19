package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.navigation.Route
import com.vkondrav.playground.app.common.navigation.basicGraph
import com.vkondrav.playground.app.drawer.viewmodel.DrawerViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.common.di.loadIntoKoin
import com.vkondrav.playground.app.drawer.composable.Drawer

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            navController.loadIntoKoin()

            MaterialTheme {
                Drawer {
                    Column {
                        CustomAppBar()
                        NavHost(
                            navController = navController,
                            startDestination = Route.Screen1.id
                        ) {
                            basicGraph()
                        }
                    }
                }
            }
        }
    }
}
