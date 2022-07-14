package com.ofek.sample.domain.feed

import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.remote.RemotePagingRequestDto
import com.ofek.sample.domain.models.remote.RemotePagingResponseDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

interface FeedRepository {
    @Throws(Exception::class)
    suspend fun fetchFeed(
        remotePagingRequest: RemotePagingRequestDto,
        feedType: FeedType
    ): RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>>

    suspend fun addPostToFavorites(post: FeedPostItemDto): RemoteResponseDto<FeedPostItemDto>
}