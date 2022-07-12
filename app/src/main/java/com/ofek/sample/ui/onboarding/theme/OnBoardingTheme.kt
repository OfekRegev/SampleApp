package com.ofek.sample.ui.onboarding.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

object OnBoardingTheme {
    val colors @Composable get() = LocalOnBoardingColors.current
    val typography @Composable get() = LocalOnBoardingTypography.current
}

@Composable
fun OnBoardingTheme(
    darkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalOnBoardingColors provides if (darkMode) darkOnBoardingColors else lightOnBoardingColors,
        content = content
    )
}