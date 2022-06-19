package com.vkondrav.ram.app.screen.drawer.composable

import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.vkondrav.ram.app.base.composable.screen.BaseStateScreen
import com.vkondrav.ram.app.screen.drawer.viewmodel.DrawerViewModel
import com.vkondrav.ram.app.screen.main.MainActivityViewModel
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun CustomDrawer(viewModel: MainActivityViewModel, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(
        initialValue = DrawerValue.Closed,
    )

    drawerState.apply { //sync the state back to state holder
        when (currentValue) {
            DrawerValue.Closed -> viewModel.closeDrawer()
            DrawerValue.Open -> viewModel.openDrawer()
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.drawerState.collectLatest {
            when (it) {
                true -> drawerState.open()
                false -> drawerState.close()
            }
        }
    }

    ModalDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        drawerContent = {
            BaseStateScreen(
                viewModel = getViewModel<DrawerViewModel>(),
            )
        },
        content = content,
    )
}
