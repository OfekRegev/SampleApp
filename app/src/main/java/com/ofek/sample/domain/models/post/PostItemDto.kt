package com.ofek.sample.domain.models.post

data class PostItemDto(
    val postId: String,
    val points: Int = 0,
    val title: String = "",
    val author: String = "",
)