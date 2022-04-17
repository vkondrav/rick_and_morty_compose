package com.vkondrav.playground.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.vkondrav.playground.app.home.composable.HomeScreen

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            MaterialTheme {
                HomeScreen()
            }
        }
    }
}
