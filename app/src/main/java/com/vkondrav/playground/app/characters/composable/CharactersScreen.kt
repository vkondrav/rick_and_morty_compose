package com.vkondrav.playground.app.characters.composable

import androidx.compose.runtime.Composable
import com.vkondrav.playground.app.base.composable.screen.BaseScreen
import com.vkondrav.playground.app.characters.viewmodel.CharactersViewModel
import com.vkondrav.playground.app.common.action.FetchDataAction
import org.koin.androidx.compose.getViewModel

@Composable
fun CharactersScreen() {
    val viewModel = getViewModel<CharactersViewModel>().also {
        it.onAction(FetchDataAction)
    }
    BaseScreen(viewModel)
}