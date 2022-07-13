package com.ofek.sample.ui.feed.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object FeedTheme {
    val colors @Composable get() = LocalArticlesColors.current
    val typography @Composable get() = LocalArticlesTypography.current
}


@Composable
fun FeedTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalArticlesColors provides if (darkMode) darkArticlesColors else lightArticlesColors,
        content = content
    )
}