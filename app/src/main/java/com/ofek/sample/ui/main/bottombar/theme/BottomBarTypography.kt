package com.ofek.sample.ui.main.bottombar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ofek.sample.ui.themes.boldFont

@Immutable
class BottomBarTypography(
    val bottomBarButtonTextStyle: TextStyle,
)

val LocalBottomBarTypography = compositionLocalOf {
    bottomBarTypography
}

val bottomBarTypography = BottomBarTypography(
    bottomBarButtonTextStyle = boldFont.copy(
        fontSize = 12.sp
    ),
)