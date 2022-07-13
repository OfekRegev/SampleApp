package com.ofek.sample.presentation.feed

import com.ofek.sample.presentation.feed.models.UiPostListItemModel

data class ArticlesViewState(
    val loading: Boolean = false,
    val items: List<UiPostListItemModel>,
)