package com.ofek.sample.ui.feed.theme

import androidx.compose.runtime.compositionLocalOf
import com.ofek.sample.ui.feed.postlist.theme.PostListItemTypography
import com.ofek.sample.ui.feed.postlist.theme.postListItemDefaultTypography

class FeedTypography(
    val postListItemTypography: PostListItemTypography,
)

val LocalArticlesTypography = compositionLocalOf {
    articlesTypography
}

val articlesTypography = FeedTypography(
    postListItemTypography = postListItemDefaultTypography
)