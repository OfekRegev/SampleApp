package com.ofek.sample.ui.postitem.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ofek.sample.ui.themes.regularFont
import javax.annotation.concurrent.Immutable

@Immutable
class PostItemTypography(
    val titleText: TextStyle,
    val pointsCountText: TextStyle,
    val authorText: TextStyle,
)


val LocalPostItemTypography = compositionLocalOf {
    postItemTypography
}


val postItemTypography = PostItemTypography(
    titleText = regularFont.copy(
        fontSize = 16.sp
    ),
    pointsCountText = regularFont.copy(
        fontSize = 16.sp
    ),
    authorText = regularFont.copy(
        fontSize = 16.sp
    ),
)