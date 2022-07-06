package com.vkondrav.ram.common.ui.design

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun DlsTheme(
    colors: DlsColorPalette = dlsDarkColorPalette,
    typography: DlsTypography = DlsTypography(),
    children: @Composable (PaddingValues) -> Unit,
) {
    CompositionLocalProvider(
        LocalDlsColors provides colors,
        LocalDlsTypography provides typography,
    ) {
        MaterialTheme(
            colors = colors.materialColors,
            typography = typography.materialTypography,
        ) {
            children(PaddingValues())
        }
    }
}

object DlsTheme {
    val colors: DlsColorPalette
        @Composable
        @ReadOnlyComposable
        get() = LocalDlsColors.current

    val typography: DlsTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalDlsTypography.current

    val sizes: DlsSize
        @Composable
        @ReadOnlyComposable
        get() = DlsSize()
}

internal val LocalDlsColors = staticCompositionLocalOf { dlsLightColorPalette }
internal val LocalDlsTypography = staticCompositionLocalOf { DlsTypography() }
