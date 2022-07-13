package com.ofek.sample.domain.models.feed

data class FeedPostItemDto(
    val postId: String,
    val points: Int = 0,
    val title: String = "",
    val views: Int = 0,
    val favorite: Boolean? = null,
)