package com.ofek.sample.ui.main.bottombar.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.primaryDarkColor
import com.ofek.sample.ui.themes.primaryLightColor
import com.ofek.sample.ui.themes.textDarkColor
import com.ofek.sample.ui.themes.textLightColor

@Immutable
class BottomBarColors(
    val bottomBarButtonSelectedTextColor: Color,
    val bottomBarButtonSelectedColor: Color,
    val bottomBarButtonUnselectedTextColor: Color,
    val bottomBarButtonUnselectedColor: Color,
)


val LocalBottomBarColors = compositionLocalOf {
    lightBottomBarColors
}

val lightBottomBarColors = BottomBarColors(
    bottomBarButtonSelectedTextColor = primaryLightColor,
    bottomBarButtonSelectedColor = primaryLightColor,
    bottomBarButtonUnselectedColor = textLightColor,
    bottomBarButtonUnselectedTextColor = textLightColor,
)

val darkBottomBarColors = BottomBarColors(
    bottomBarButtonSelectedTextColor = primaryDarkColor,
    bottomBarButtonSelectedColor = primaryDarkColor,
    bottomBarButtonUnselectedColor = textDarkColor,
    bottomBarButtonUnselectedTextColor = textDarkColor,
)