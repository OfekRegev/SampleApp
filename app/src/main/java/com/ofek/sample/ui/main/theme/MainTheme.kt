package com.ofek.sample.ui.main.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object MainTheme {
    val colors: MainColors @Composable get() = LocalMainColors.current
}

@Composable
fun MainTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalMainColors provides if (darkMode) darkMainColors else lightMainColors,
        content = content
    )
}