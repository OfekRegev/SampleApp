package com.ofek.sample.ui.postitem.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.primaryDarkColor
import com.ofek.sample.ui.themes.primaryLightColor
import com.ofek.sample.ui.themes.textDarkColor
import com.ofek.sample.ui.themes.textLightColor
import javax.annotation.concurrent.Immutable

@Immutable
class PostItemColors(
    val titleTextColor: Color,
    val viewsCountTextColor: Color,
    val authorTextColor: Color,
    val pointsCountTextColor: Color,
    val progressBarColor: Color
)

val LocalPostItemColors = compositionLocalOf {
    lightPostItemColors
}

val lightPostItemColors = PostItemColors(
    titleTextColor = textLightColor,
    viewsCountTextColor = textLightColor,
    authorTextColor = textLightColor,
    pointsCountTextColor = textLightColor,
    progressBarColor = primaryLightColor
)
val darkPostItemColors = PostItemColors(
    titleTextColor = textDarkColor,
    viewsCountTextColor = textDarkColor,
    authorTextColor = textDarkColor,
    pointsCountTextColor = textDarkColor,
    progressBarColor = primaryDarkColor
)
