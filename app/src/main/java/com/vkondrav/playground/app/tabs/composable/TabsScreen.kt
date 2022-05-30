package com.vkondrav.playground.app.tabs.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.vkondrav.playground.app.common.navigation.Screen

@Composable
fun TabsScreen() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = emptyList<Screen>()
    val pagerState = rememberPagerState()

    Column {
        ScrollableTabRow(selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(
                        pagerState,
                        tabPositions
                    )
                )
            }) {
            tabs.forEachIndexed { index, _ ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = "") })
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
