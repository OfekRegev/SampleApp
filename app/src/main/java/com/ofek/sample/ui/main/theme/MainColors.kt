package com.ofek.sample.ui.main.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.black
import com.ofek.sample.ui.themes.white

class MainColors(
    val backgroundColor : Color,
)

val LocalMainColors = compositionLocalOf {
    lightMainColors
}

val lightMainColors = MainColors(
    backgroundColor = white
)

val darkMainColors = MainColors(
    backgroundColor = black
)