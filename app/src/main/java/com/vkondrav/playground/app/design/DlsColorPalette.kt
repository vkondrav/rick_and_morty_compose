package com.vkondrav.playground.app.design

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

interface DlsColorPalette {
    val primary: Color
    val background: Color
    val basic: Color
    val disable: Color
    val text: Color
    val textReverse: Color
    val success: Color
    val link: Color
    val warning: Color
    val error: Color

    val materialColors: Colors
}

fun dlsLightColorPalette(): DlsColorPalette = object : DlsColorPalette {
    override val primary: Color = DlsColors.primary
    override val background: Color = DlsColors.background
    override val basic: Color = DlsColors.basic
    override val disable: Color = DlsColors.disable
    override val text: Color = DlsColors.text
    override val textReverse: Color = DlsColors.textReverse
    override val success: Color = DlsColors.success
    override val link: Color = DlsColors.link
    override val warning: Color = DlsColors.warning
    override val error: Color = DlsColors.error

    override val materialColors: Colors = lightColors(
        primary = DlsColors.primary,
        background = DlsColors.background,
        surface = DlsColors.background,
        onSurface = DlsColors.text,
        onBackground = DlsColors.text,
    )
}

fun dlsDarkColorPalette(): DlsColorPalette = object : DlsColorPalette {
    override val primary: Color = DlsColors.primary
    override val background: Color = DlsColors.backgroundReverse
    override val basic: Color = DlsColors.basic
    override val disable: Color = DlsColors.disable
    override val text: Color = DlsColors.textReverse
    override val textReverse: Color = DlsColors.text
    override val success: Color = DlsColors.success
    override val link: Color = DlsColors.link
    override val warning: Color = DlsColors.warning
    override val error: Color = DlsColors.error

    override val materialColors: Colors = darkColors(
        primary = DlsColors.primary,
        background = DlsColors.backgroundReverse,
        surface = DlsColors.backgroundReverse,
        onSurface = DlsColors.textReverse,
        onBackground = DlsColors.textReverse,
    )
}
