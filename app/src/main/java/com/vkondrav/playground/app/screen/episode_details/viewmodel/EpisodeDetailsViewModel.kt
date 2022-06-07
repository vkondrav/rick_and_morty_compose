package com.vkondrav.playground.app.screen.episode_details.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.screen.episode_details.usecase.EpisodeDetailsSource
import com.vkondrav.playground.app.base.viewmodel.ScreenState
import com.vkondrav.playground.app.base.viewmodel.ScreenStateViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class EpisodeDetailsViewModel(
    episodeId: String,
    episodeDetailsSource: EpisodeDetailsSource,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenStateViewModel {

    private var _screenState = mutableStateOf<ScreenState>(ScreenState.Loading())
    override val screenState: State<ScreenState> = _screenState

    override val items: StateFlow<List<ComposableItem>> =
        episodeDetailsSource(episodeId).getOrElse {
            _screenState.value = ScreenState.Error(PageErrorViewItem(it))
            emptyFlow()
        }.catch {
            _screenState.value = ScreenState.Error(PageErrorViewItem(it))
        }.map { characterDetails ->
            _screenState.value = ScreenState.Content
            characterDetails
        }.distinctUntilChanged().stateIn(
            scope = this,
            started = SharingStarted.WhileSubscribed(),
            initialValue = emptyList(),
        )
}
