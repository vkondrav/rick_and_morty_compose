package com.vkondrav.ram.app.design

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
    val textSubtle: Color
    val textReverse: Color
    val textSubtleReverse: Color
    val success: Color
    val link: Color
    val warning: Color
    val error: Color

    val materialColors: Colors
}

val dlsLightColorPalette: DlsColorPalette get() = object : DlsColorPalette {
    override val primary: Color = DlsColors.primary
    override val background: Color = DlsColors.background
    override val basic: Color = DlsColors.basic
    override val disable: Color = DlsColors.disable
    override val text: Color = DlsColors.text
    override val textSubtle: Color = DlsColors.textSubtle
    override val textReverse: Color = DlsColors.textReverse
    override val textSubtleReverse: Color = DlsColors.textSubtleReverse
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

val dlsDarkColorPalette: DlsColorPalette get() = object : DlsColorPalette {
    override val primary: Color = DlsColors.primary
    override val background: Color = DlsColors.backgroundReverse
    override val basic: Color = DlsColors.basic
    override val disable: Color = DlsColors.disable
    override val text: Color = DlsColors.textReverse
    override val textSubtle: Color = DlsColors.textSubtleReverse
    override val textReverse: Color = DlsColors.text
    override val textSubtleReverse: Color = DlsColors.textSubtle
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
