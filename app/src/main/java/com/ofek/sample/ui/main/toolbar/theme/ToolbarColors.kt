package com.ofek.sample.ui.main.toolbar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.black
import com.ofek.sample.ui.themes.primaryDarkColor
import com.ofek.sample.ui.themes.primaryLightColor
import com.ofek.sample.ui.themes.white

@Immutable
class ToolbarColors(
    val toolbarTitleTextColor: Color,
    val toolbarBackgroundColor: Color,
)


val LocalToolbarColors = compositionLocalOf {
    lightToolbarColors
}

val lightToolbarColors = ToolbarColors(
    toolbarTitleTextColor = white,
    toolbarBackgroundColor = primaryLightColor
)

val darkToolbarColors = ToolbarColors(
    toolbarTitleTextColor = black,
    toolbarBackgroundColor = primaryDarkColor
)