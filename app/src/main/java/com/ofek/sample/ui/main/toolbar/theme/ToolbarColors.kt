package com.ofek.sample.ui.main.toolbar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.main.theme.black
import com.ofek.sample.ui.main.theme.white

@Immutable
class ToolbarColors(
    val toolbarTitleTextColor: Color
)


val LocalToolbarColors = compositionLocalOf {
    lightToolbarColors
}

val lightToolbarColors = ToolbarColors(
    toolbarTitleTextColor = white,
)

val darkToolbarColors = ToolbarColors(
    toolbarTitleTextColor = black,
)