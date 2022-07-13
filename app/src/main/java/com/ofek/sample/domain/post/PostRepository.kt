package com.ofek.sample.domain.post

import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

interface PostRepository {
    suspend fun addPostToFavorites(postId: PostItemDto): RemoteResponseDto<PostItemDto>
    suspend fun fetchPost(postId: String): RemoteResponseDto<PostItemDto>
}