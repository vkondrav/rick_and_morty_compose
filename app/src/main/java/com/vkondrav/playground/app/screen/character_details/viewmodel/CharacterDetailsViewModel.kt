package com.vkondrav.playground.app.screen.character_details.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.screen.character_details.usecase.CharacterDetailsSource
import com.vkondrav.playground.app.base.viewmodel.ScreenState
import com.vkondrav.playground.app.base.viewmodel.ScreenStateViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map

class CharacterDetailsViewModel(
    private val characterId: String,
    private val characterDetailsSource: CharacterDetailsSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    override var screenState = mutableStateOf<ScreenState>(ScreenState.Loading())

    override val items: Flow<List<ComposableItem>>
        get() = characterDetailsSource(characterId).getOrElse {
            screenState.value = ScreenState.Error(PageErrorViewItem(it))
            emptyFlow()
        }.catch {
            screenState.value = ScreenState.Error(PageErrorViewItem(it))
        }.map { characterDetails ->
            screenState.value = ScreenState.Content
            characterDetails
        }
}
