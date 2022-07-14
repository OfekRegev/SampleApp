package com.ofek.sample.presentation.postitem.models

import com.ofek.sample.domain.models.post.PostItemDto

fun mapPostItemDtoToPostItemModel(postItemDto: PostItemDto): UiPostItemModel {
    return UiPostItemModel(
        title = postItemDto.title,
        author = postItemDto.author,
        viewsCount = postItemDto.views,
        points = postItemDto.points,
    )
}