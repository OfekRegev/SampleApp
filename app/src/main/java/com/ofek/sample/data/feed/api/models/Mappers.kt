package com.ofek.sample.data.feed.api.models

import com.ofek.sample.domain.models.feed.FeedPostItemDto

fun mapApiSearchItemToFeedPostItemDto(
    apiPostItem: ApiSearchPostItem,
): FeedPostItemDto? {
    // mandatory field
    if (apiPostItem.objectId == null) {
        return null
    }
    return FeedPostItemDto(
        postId = apiPostItem.objectId,
        points = apiPostItem.points ?: 0,
        title = apiPostItem.title.orEmpty(),
    )
}