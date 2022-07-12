package com.ofek.sample.ui.main.bottombar.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object BottomBarTheme {
    val colors @Composable get() = LocalBottomBarColors.current
    val typography @Composable get() = LocalBottomBarTypography.current
}

@Composable
fun BottomBarTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalBottomBarColors provides if (darkMode) darkBottomBarColors else lightBottomBarColors,
        content = content,
    )
}