package com.vkondrav.ram.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.DrawerValue
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.remember
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.vkondrav.ram.app.common.state.LoadStateIntoKoin
import com.vkondrav.ram.app.design.rememberThemeState
import com.vkondrav.ram.app.screen.main.MainActivityScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            val navHostController = rememberAnimatedNavController()
            val snackbarHostState = remember { SnackbarHostState() }
            val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()

            val themeState = rememberThemeState(
                initialValue = isSystemInDarkTheme(),
            )

            val drawerState = rememberDrawerState(
                initialValue = DrawerValue.Closed,
            )

            LoadStateIntoKoin(
                navHostController,
                snackbarHostState,
                drawerState,
            )

            MainActivityScreen(
                navHostController,
                snackbarHostState,
                bottomSheetScaffoldState,
                themeState,
                drawerState,
            )
        }
    }
}


