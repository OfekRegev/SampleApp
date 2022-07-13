package com.ofek.sample.presentation.feed.models

import com.ofek.sample.domain.models.feed.FeedPostItemDto

fun mapFeedPostItemDtoToUiPostListItemModel(
    feedPostItemDto: FeedPostItemDto,
    onClick: (id: String) -> Unit,
): UiPostListItemModel {
    return UiPostListItemModel(
        itemId = feedPostItemDto.postId,
        title = feedPostItemDto.title,
        counterText = feedPostItemDto.points.toString(),
        favorite = feedPostItemDto.favorite ?: false,
        onClick = onClick
    )
}