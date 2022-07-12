package com.ofek.sample.ui.main.toolbar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ofek.sample.ui.themes.boldFont

@Immutable
class ToolbarTypography(
    val toolbarTitleTextStyle: TextStyle,
)

val LocalToolbarTypography = compositionLocalOf {
    toolbarTypography
}

val toolbarTypography = ToolbarTypography(
    toolbarTitleTextStyle = boldFont.copy(
        fontSize = 16.sp
    ),
)