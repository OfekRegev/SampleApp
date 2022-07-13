package com.ofek.sample.presentation.feed

import com.ofek.sample.presentation.feed.models.UiPostListItemModel

data class FeedState(
    val items: List<UiPostListItemModel> = emptyList(),
    val currentPage: Int = 0,
    val isLoading: Boolean = false,
    val canLoadMore: Boolean = true,
)