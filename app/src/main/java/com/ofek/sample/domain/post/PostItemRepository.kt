package com.ofek.sample.domain.post

import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

interface PostItemRepository {
    suspend fun fetchPost(postId: String): RemoteResponseDto<PostItemDto>
}