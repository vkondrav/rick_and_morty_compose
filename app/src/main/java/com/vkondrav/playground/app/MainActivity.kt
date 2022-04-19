package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.vkondrav.playground.app.common.appbar.CustomAppBar
import com.vkondrav.playground.app.common.navigation.Route
import com.vkondrav.playground.app.common.navigation.basicGraph
import com.vkondrav.playground.app.common.navigation.loadIntoKoin
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navController = rememberNavController()
            navController.loadIntoKoin()

            MaterialTheme {
                Column {
                    CustomAppBar(
                        navController = navController,
                    )
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
