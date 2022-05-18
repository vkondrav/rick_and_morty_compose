package com.vkondrav.playground.app.screen.episodes.viewmodel

import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.composable.PageLoadingViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.episodes.composable.EpisodeViewItem
import com.vkondrav.playground.app.screen.episodes.usecase.FetchEpisodesUseCase
import com.vkondrav.playground.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.playground.graphql.ram.domain.RamEpisode
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class EpisodesViewModel(
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
    private val navigateToEpisodeDetailsUseCase: NavigateToEpisodeDetailsUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchEpisodes() {
        launch {
            _screenEvent.value = ScreenEvent.Loading(PageLoadingViewItem)
            fetchEpisodesUseCase(page = 0).getOrElse { error ->
                _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                return@launch
            }.let { items ->
                _screenEvent.value = ScreenEvent.Column(items.viewItems)
            }
        }
    }

    private val List<RamEpisode>.viewItems
        get() = map { episode ->
            EpisodeViewItem(
                episode = episode,
                onClickAction = {
                    navigateToEpisodeDetailsUseCase(
                        id = episode.id,
                        title = episode.title,
                    )
                },
            )
        }
}