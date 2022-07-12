package com.ofek.sample.ui.postlist.theme

import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.textDarkColor
import com.ofek.sample.ui.themes.textLightColor

class PostListItemColors(
    val titleTextColor: Color,
    val counterTextColor: Color,
)

val lightPostListColors = PostListItemColors(
    titleTextColor = textLightColor,
    counterTextColor = textLightColor,
)
val darkPostListColors = PostListItemColors(
    titleTextColor = textDarkColor,
    counterTextColor = textDarkColor,
)



