package com.vkondrav.ram.app.common.bottom_sheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DragHandle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vkondrav.ram.app.R
import com.vkondrav.ram.app.design.DlsColors
import com.vkondrav.ram.app.design.DlsTheme
import com.vkondrav.ram.app.design.dlsDarkColorPalette
import com.vkondrav.ram.app.design.dlsLightColorPalette
import com.vkondrav.ram.app.design.ThemeState
import com.vkondrav.ram.app.tabs.composable.FavoriteTabsScreen

@Composable
fun BottomSheet(
    bottomSheetState: BottomSheetScaffoldState,
    themeState: ThemeState,
    content: @Composable (PaddingValues) -> Unit,
) {
    val isThemeDark = themeState.isThemeDark.value

    DlsTheme(
        colors = when (isThemeDark) {
            true -> dlsLightColorPalette()
            false -> dlsDarkColorPalette()
        },
    ) {
        BottomSheetScaffold(
            backgroundColor = when (isThemeDark) {
                true -> DlsColors.backgroundReverse
                false -> DlsColors.background
            },
            scaffoldState = bottomSheetState,
            sheetContent = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MAX_SHEET_HEIGHT),
                ) {
                    Icon(
                        imageVector = Icons.Default.DragHandle,
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )
                    Text(
                        text = stringResource(id = R.string.favorites),
                        style = DlsTheme.typography.headline5,
                        color = DlsTheme.colors.text,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )
                    FavoriteTabsScreen()
                }
            },
            sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
            sheetPeekHeight = PEEK_HEIGHT,
            content = {
                DlsTheme(
                    colors = when (isThemeDark) {
                        true -> dlsDarkColorPalette()
                        false -> dlsLightColorPalette()
                    },
                    children = content,
                )
            },
        )
    }
}

private val MAX_SHEET_HEIGHT = 300.dp
private val PEEK_HEIGHT = 100.dp