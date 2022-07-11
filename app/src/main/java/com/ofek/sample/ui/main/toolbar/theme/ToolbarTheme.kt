package com.ofek.sample.ui.main.toolbar.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object ToolbarTheme {
    val colors @Composable get() = LocalToolbarColors.current
    val typography @Composable get() = LocalToolbarTypography.current
}

@Composable
fun ToolbarTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalToolbarColors provides if (darkMode) lightToolbarColors else darkToolbarColors,
        content = content,
    )
}