package com.ofek.sample.presentation.articles

import com.ofek.sample.presentation.postlist.UiPostListItemModel

data class ArticlesViewState(
    val loading: Boolean = false,
    val items: List<UiPostListItemModel>,
)