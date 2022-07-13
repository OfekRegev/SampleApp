package com.ofek.sample.ui.feed.postlist.theme

import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.primaryDarkColor
import com.ofek.sample.ui.themes.primaryLightColor
import com.ofek.sample.ui.themes.textDarkColor
import com.ofek.sample.ui.themes.textLightColor

class PostListItemColors(
    val titleTextColor: Color,
    val counterTextColor: Color,
    val itemBackgroundColor: Color,
    val favoriteIconColor: Color
)

val lightDefaultPostListColors = PostListItemColors(
    titleTextColor = textLightColor,
    counterTextColor = textLightColor,
    itemBackgroundColor = primaryLightColor,
    favoriteIconColor = textLightColor,
)
val darkDefaultPostListColors = PostListItemColors(
    titleTextColor = textDarkColor,
    counterTextColor = textDarkColor,
    itemBackgroundColor = primaryDarkColor,
    favoriteIconColor = textDarkColor,
)



