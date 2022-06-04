package com.vkondrav.playground.app.screen.character_details.usecase

import com.vkondrav.playground.app.R
import com.vkondrav.playground.app.base.item.ComposableItem
import com.vkondrav.playground.app.base.item.ContentViewItem
import com.vkondrav.playground.app.common.composable.CollapsableViewItem
import com.vkondrav.playground.app.common.state.AppState
import com.vkondrav.playground.app.common.utils.TextResource
import com.vkondrav.playground.app.screen.character_details.composable.CharacterDetailsViewItem
import com.vkondrav.playground.app.screen.episodes.composable.EpisodeViewItem
import com.vkondrav.playground.app.screen.episodes.usecase.NavigateToEpisodeDetailsUseCase
import com.vkondrav.playground.app.screen.locations.usecase.TransformLocationsUseCase
import com.vkondrav.playground.graphql.ram.domain.RamCharacterDetails
import com.vkondrav.playground.graphql.ram.domain.RamEpisode
import com.vkondrav.playground.graphql.ram.domain.RamLocation
import com.vkondrav.playground.room.ram.FavoriteEpisode
import com.vkondrav.playground.room.ram.FavoriteEpisodesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

class RemoveEpisodeToFavoritesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val appState: AppState,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(episode: RamEpisode) {
        launch {
            favoriteEpisodesDao.delete(episode.id)
            appState.showSnackbar("${episode.title} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }
}

class AddEpisodeToFavoritesUseCase(
    private val favoriteEpisodesDao: FavoriteEpisodesDao,
    private val appState: AppState,
    private val dispatcher: CoroutineDispatcher,
) : CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = dispatcher + exceptionHandler

    operator fun invoke(episode: RamEpisode) {
        launch {
            favoriteEpisodesDao.insert(episode.favoriteEpisode)
            appState.showSnackbar("${episode.title} added to favorites")
        }
    }

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Timber.e(throwable)
    }

    private val RamEpisode.favoriteEpisode
        get() = FavoriteEpisode(
            id = id,
            title = title,
            airDate = airDate,
        )
}

class HandleEpisodeFavoritesUseCase(
    private val addEpisodeToFavoriteUseCase: AddEpisodeToFavoritesUseCase,
    private val removeEpisodeToFavoritesUseCase: RemoveEpisodeToFavoritesUseCase,
) {
    operator fun invoke(isFavorite: Boolean, episode: RamEpisode) {
        when (isFavorite) {
            true -> addEpisodeToFavoriteUseCase(episode)
            false -> removeEpisodeToFavoritesUseCase(episode)
        }
    }
}

class TransformEpisodesUseCase(
    private val navigateToEpisodeDetailsUseCase: NavigateToEpisodeDetailsUseCase,
    private val handleEpisodeFavoritesUseCase: HandleEpisodeFavoritesUseCase,
) {

    operator fun invoke(episode: RamEpisode): ComposableItem = episode.viewItem

    operator fun invoke(episodes: List<RamEpisode>): List<ComposableItem> =
        episodes.map { it.viewItem }

    private val RamEpisode.viewItem
        get() = EpisodeViewItem(
            episode = this,
            onClickAction = {
                navigateToEpisodeDetailsUseCase(
                    id,
                    title,
                )
            },
            onFavoriteAction = { isFavorite ->
                handleEpisodeFavoritesUseCase(isFavorite, episode = this)
            },
        )
}

class TransformCharacterDetailsUseCase(
    private val transformLocationsUseCase: TransformLocationsUseCase,
    private val transformEpisodesUseCase: TransformEpisodesUseCase,
) {

    operator fun invoke(
        details: RamCharacterDetails,
    ): ComposableItem = ContentViewItem(
        items = with(details) {
            listOfNotNull(
                header,
                currentLocation,
                originLocation,
                episodesList,
            )
        },
    )

    private val RamCharacterDetails.header
        get() = CharacterDetailsViewItem(
            character = character,
        )

    private val RamCharacterDetails.currentLocation
        get() = location?.run {
            locationItem(TextResource.Resource(R.string.current_location))
        }

    private val RamCharacterDetails.originLocation
        get() = origin?.run {
            locationItem(TextResource.Resource(R.string.origin))
        }

    private val RamCharacterDetails.episodesList
        get() = CollapsableViewItem(
            title = TextResource.Resource(R.string.episodes),
            items = transformEpisodesUseCase(episodes.takeLast(MAX_EPISODES).reversed()),
            open = false,
        )

    private fun RamLocation.locationItem(title: TextResource) = CollapsableViewItem(
        title = title,
        items = listOf(transformLocationsUseCase(this)),
        open = false,
    )

    companion object {
        private const val MAX_EPISODES = 5
    }
}
