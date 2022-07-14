package com.ofek.sample.data.postitem.api.models

import com.ofek.sample.domain.models.post.PostItemDto


fun mapApiPostItemToPostItemDto(
    apiPostItem: ApiPostItem
) : PostItemDto {
    return PostItemDto(
        postId = apiPostItem.id.toString(),
        points = apiPostItem.points,
        title = apiPostItem.title,
        author = apiPostItem.author
    )
}