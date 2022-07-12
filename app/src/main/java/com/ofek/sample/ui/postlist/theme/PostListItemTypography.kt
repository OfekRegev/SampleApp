package com.ofek.sample.ui.postlist.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ofek.sample.ui.themes.regularFont

class PostListItemTypography(
    val titleTextStyle: TextStyle,
    val pointsTextStyle: TextStyle,
)


val postListItemTypography = PostListItemTypography(
    titleTextStyle = regularFont.copy(
        fontSize = 16.sp,
    ),
    pointsTextStyle = regularFont.copy(
        fontSize = 16.sp,
    ),
)