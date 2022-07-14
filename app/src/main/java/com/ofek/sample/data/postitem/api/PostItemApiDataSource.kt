package com.ofek.sample.data.postitem.api

import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

interface PostItemApiDataSource {
    suspend fun fetchPostItem(postId: String): RemoteResponseDto<PostItemDto>
}