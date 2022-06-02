package com.vkondrav.playground.app.screen.episode_details.viewmodel

import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.episode_details.composable.EpisodeDetailsViewItem
import com.vkondrav.playground.app.screen.episode_details.usecase.FetchEpisodeDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class EpisodeDetailsViewModel(
    private val fetchEpisodeDetailsUseCase: FetchEpisodeDetailsUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchEpisodeDetails(id: String) {
        launch {
            _screenEvent.value = ScreenEvent.Loading()
            fetchEpisodeDetailsUseCase(id)
                .onSuccess { details ->
                    _screenEvent.value = ScreenEvent.Content(
                        EpisodeDetailsViewItem(
                            episode = details.episode,
                        ),
                    )
                }
                .onFailure { error ->
                    _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                }
        }
    }
}
