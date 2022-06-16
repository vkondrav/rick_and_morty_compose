package com.vkondrav.ram.app.tabs.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.vkondrav.ram.app.screen.favorite_characters.nav.favoriteCharactersTab
import com.vkondrav.ram.app.screen.favorite_episodes.nav.favoriteEpisodesTab
import com.vkondrav.ram.app.screen.favorite_locations.nav.favoriteLocationsTab
import kotlinx.coroutines.launch

@Composable
fun FavoriteTabsScreen() {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf(
        favoriteCharactersTab,
        favoriteEpisodesTab,
        favoriteLocationsTab,
    )

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = MaterialTheme.colors.background,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions,
                    ),
                )
            },
        ) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    selected = tabIndex == index,
                    onClick = {
                        tabIndex = index
                        scope.launch {
                            pagerState.scrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = tab.title.string())
                    },
                )
            }
        }
        HorizontalPager(
            count = tabs.size,
            state = pagerState,
        ) { index ->
            tabs[index].compose(null)
        }
    }
}
