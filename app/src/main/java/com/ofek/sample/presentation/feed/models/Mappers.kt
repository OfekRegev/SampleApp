package com.ofek.sample.presentation.feed.models

import com.ofek.sample.domain.feed.FeedType
import com.ofek.sample.domain.models.feed.FeedPostItemDto

fun mapFeedPostItemDtoToUiPostListItemModel(
    feedPostItemDto: FeedPostItemDto,
    feedType: FeedType,
    onClick: () -> Unit,
): UiPostListItemModel {
    return UiPostListItemModel(
        itemId = feedPostItemDto.postId,
        title = feedPostItemDto.title,
        counterText = if (feedType == FeedType.FAVORITES) feedPostItemDto.views.toString() else feedPostItemDto.points.toString(),
        favorite = feedPostItemDto.favorite ?: false,
        onClick = onClick
    )
}