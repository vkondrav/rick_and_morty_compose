package com.vkondrav.ram.app.screen.episode_details.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.vkondrav.ram.app.base.item.ComposableItem
import com.vkondrav.ram.app.base.viewmodel.BaseViewModel
import com.vkondrav.ram.app.common.composable.PageErrorViewItem
import com.vkondrav.ram.app.screen.episode_details.usecase.EpisodeDetailsSource
import com.vkondrav.ram.app.base.viewmodel.ScreenState
import com.vkondrav.ram.app.base.viewmodel.ScreenStateViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow

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
        }.mapState(initialValue = emptyList()) { items ->

            _screenState.value = when {
                items.isEmpty() -> ScreenState.Error(
                    PageErrorViewItem(
                        Exception("Empty Data"),
                    ),
                )
                else -> ScreenState.Content
            }

        }
}