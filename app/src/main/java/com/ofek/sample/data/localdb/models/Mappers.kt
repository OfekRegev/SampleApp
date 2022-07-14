package com.ofek.sample.data.localdb.models

import com.ofek.sample.domain.models.feed.FeedPostItemDto

fun mapLocalDbPostToFeedPostItemDto(
    localDbPost: LocalDbPost
): FeedPostItemDto {
    return FeedPostItemDto(
        localDbPost.postId,
        localDbPost.points,
        localDbPost.title,
        localDbPost.views,
        true
    )
}

fun mapLocalDbPostToPostItemDto(
    localDbPost: LocalDbPost
): FeedPostItemDto {
    return FeedPostItemDto(
        localDbPost.postId,
        localDbPost.points,
        localDbPost.title,
        localDbPost.views,
        true
    )
}

fun mapFeedPostItemDtoToLocalDbPost(
    postItemDto: FeedPostItemDto
): LocalDbPost {
    return LocalDbPost(
        postId = postItemDto.postId,
        title = postItemDto.title,
        points = postItemDto.points,
        views = postItemDto.views,
    )
}