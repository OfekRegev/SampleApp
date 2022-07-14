package com.ofek.sample.presentation.postitem

import com.ofek.sample.presentation.postitem.models.UiPostItemModel

data class PostItemState(
    val loading: Boolean = false,
    val postItemModel: UiPostItemModel? = null,
)