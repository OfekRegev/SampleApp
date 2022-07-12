package com.ofek.sample.ui.onboarding.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import com.ofek.sample.ui.themes.*

@Immutable
class OnBoardingColors(
    val navigationButtonBackgroundColor: Color,
    val navigationButtonTextColor: Color,
)

val LocalOnBoardingColors = compositionLocalOf {
    lightOnBoardingColors
}

val lightOnBoardingColors = OnBoardingColors(
    navigationButtonBackgroundColor = primaryLightColor,
    navigationButtonTextColor = textLightColor,
)

val darkOnBoardingColors = OnBoardingColors(
    navigationButtonBackgroundColor = primaryDarkColor,
    navigationButtonTextColor = textDarkColor,
)
