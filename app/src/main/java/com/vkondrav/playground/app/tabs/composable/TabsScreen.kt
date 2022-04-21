package com.vkondrav.playground.app.tabs.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.vkondrav.playground.app.page1.nav.page1Screen
import com.vkondrav.playground.app.page2.nav.page2Screen
import com.vkondrav.playground.app.page3.nav.page3Screen

@Composable
fun TabsScreen() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        page1Screen,
        page2Screen,
        page3Screen,
    )
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
            tabs.forEachIndexed { index, screen ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = screen.title) })
            }
        }
        HorizontalPager(
            count = tabs.size,
            state = pagerState,
        ) { index ->
            tabs[index].Composable(action = { })
        }
    }
}