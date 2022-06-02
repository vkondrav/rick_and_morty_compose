package com.vkondrav.playground.app.screen.location_details.viewmodel

import com.vkondrav.playground.app.base.viewmodel.BaseViewModel
import com.vkondrav.playground.app.base.viewmodel.ScreenEventViewModel
import com.vkondrav.playground.app.common.composable.PageErrorViewItem
import com.vkondrav.playground.app.common.event.ScreenEvent
import com.vkondrav.playground.app.screen.location_details.composable.LocationDetailsViewItem
import com.vkondrav.playground.app.screen.location_details.usecase.FetchLocationDetailsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

class LocationDetailsViewModel(
    private val fetchLocationDetailsUseCase: FetchLocationDetailsUseCase,
    dispatcher: CoroutineDispatcher,
) : BaseViewModel(dispatcher), ScreenEventViewModel {

    private val _screenEvent = MutableStateFlow<ScreenEvent?>(null)
    override val screenEvent: Flow<ScreenEvent> = _screenEvent.filterNotNull()

    fun fetchLocationDetails(id: String) {
        launch {
            _screenEvent.value = ScreenEvent.Loading()
            fetchLocationDetailsUseCase(id)
                .onSuccess { details ->
                    _screenEvent.value = ScreenEvent.Content(
                        LocationDetailsViewItem(
                            location = details.location,
                        ),
                    )
                }
                .onFailure { error ->
                    _screenEvent.value = ScreenEvent.Error(PageErrorViewItem(error))
                }
        }
    }
}
