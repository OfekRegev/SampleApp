package com.ofek.sample.data.postitem

import com.ofek.sample.data.postitem.api.PostItemApiDataSource
import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto
import com.ofek.sample.domain.post.PostItemRepository

class PostItemRepositoryImpl(
    private val dataSource: PostItemApiDataSource,
) : PostItemRepository {

    override suspend fun fetchPost(postId: String): RemoteResponseDto<PostItemDto> {
        return dataSource.fetchPostItem(postId)
    }

}