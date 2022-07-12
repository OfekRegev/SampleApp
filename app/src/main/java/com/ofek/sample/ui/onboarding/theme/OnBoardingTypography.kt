package com.ofek.sample.ui.onboarding.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ofek.sample.ui.themes.regularFont

@Immutable
class OnBoardingTypography(
    val navigationButtonTextStyle: TextStyle,
)


val LocalOnBoardingTypography = compositionLocalOf {
    onBoardingTypography
}

val onBoardingTypography = OnBoardingTypography(
    navigationButtonTextStyle = regularFont.copy(
        fontSize = 16.sp
    )
)