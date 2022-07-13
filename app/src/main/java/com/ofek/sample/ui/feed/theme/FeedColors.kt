package com.ofek.sample.ui.feed.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.feed.postlist.theme.PostListItemColors
import com.ofek.sample.ui.feed.postlist.theme.darkDefaultPostListColors
import com.ofek.sample.ui.feed.postlist.theme.lightDefaultPostListColors
import com.ofek.sample.ui.themes.primaryDarkColor
import com.ofek.sample.ui.themes.primaryLightColor

class FeedColors(
    val postListItemColors: PostListItemColors,
    val listLoaderColor: Color,
)


val LocalArticlesColors = compositionLocalOf {
    lightArticlesColors
}


val lightArticlesColors = FeedColors(
    postListItemColors = lightDefaultPostListColors,
    listLoaderColor = primaryLightColor,
)

val darkArticlesColors = FeedColors(
    postListItemColors = darkDefaultPostListColors,
    listLoaderColor = primaryDarkColor,
)