package com.ofek.sample.presentation.feed.models

class UiPostListItemModel(
    val itemId: String,
    val title: String,
    val counterText: String,
    val favorite: Boolean,
    val onClick: () -> Unit,
)