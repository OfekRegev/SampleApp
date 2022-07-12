package com.ofek.sample.ui.main.toolbar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.primaryDarkColor
import com.ofek.sample.ui.themes.primaryLightColor
import com.ofek.sample.ui.themes.textDarkColor
import com.ofek.sample.ui.themes.textLightColor

@Immutable
class ToolbarColors(
    val toolbarTitleTextColor: Color,
    val toolbarBackgroundColor: Color,
    val toolbarButtonColor: Color,
)


val LocalToolbarColors = compositionLocalOf {
    lightToolbarColors
}

val lightToolbarColors = ToolbarColors(
    toolbarTitleTextColor = textLightColor,
    toolbarBackgroundColor = primaryLightColor,
    toolbarButtonColor = textLightColor,
)

val darkToolbarColors = ToolbarColors(
    toolbarTitleTextColor = textDarkColor,
    toolbarBackgroundColor = primaryDarkColor,
    toolbarButtonColor = textLightColor,
)