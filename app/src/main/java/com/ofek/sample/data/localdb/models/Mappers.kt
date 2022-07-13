package com.ofek.sample.data.localdb.models

import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.post.PostItemDto

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
): PostItemDto {
    return PostItemDto(
        localDbPost.postId,
        localDbPost.points,
        localDbPost.title,
        localDbPost.author,
        localDbPost.views,
        true
    )
}

fun mapPostItemDtoToLocalDbPost(
    postItemDto: PostItemDto
): LocalDbPost {
    return LocalDbPost(
        postId = postItemDto.postId,
        title = postItemDto.title,
        points = postItemDto.points,
        views = postItemDto.views,
        author = postItemDto.author
    )
}