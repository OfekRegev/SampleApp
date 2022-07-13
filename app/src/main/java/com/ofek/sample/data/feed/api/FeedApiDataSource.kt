package com.ofek.sample.data.feed.api

import com.ofek.sample.domain.feed.FeedType
import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.remote.RemotePagingResponseDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

interface FeedApiDataSource {

    @Throws(Exception::class)
    suspend fun fetchFeed(
        page: Int,
        feedType: FeedType
    ): RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>>

}