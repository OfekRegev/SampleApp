package com.ofek.sample.ui.postitem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object PostItemTheme {
    val colors @Composable get() = LocalPostItemColors.current
    val typography @Composable get() = LocalPostItemTypography.current
}

@Composable
fun PostItemTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPostItemColors provides if (darkMode) darkPostItemColors else lightPostItemColors,
        content = content
    )
}