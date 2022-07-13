package com.ofek.sample.data.feed.api

import com.ofek.sample.data.exceptions.ApiException
import com.ofek.sample.data.exceptions.ApiHttpResponseErrorException
import com.ofek.sample.data.feed.api.models.mapApiSearchItemToFeedPostItemDto
import com.ofek.sample.domain.feed.FeedType
import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.remote.RemotePagingResponseDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

class FeedApiDataSourceImpl(
    private val service: FeedApiService
) : FeedApiDataSource {
    companion object {
        private const val ARTICLES_TAG = "front_page"
        private const val STORIES_TAG = "story"
        private const val ARTICLES_PATH = "search"
        private const val STORIES_PATH = "search_by_date"
    }

    @Throws(Exception::class)
    override suspend fun fetchFeed(
        page: Int,
        feedType: FeedType
    ): RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>> {
        val tag: String
        val path: String
        when (feedType) {
            FeedType.ARTICLES -> {
                tag = ARTICLES_TAG
                path = ARTICLES_PATH
            }
            FeedType.STORIES -> {
                tag = STORIES_TAG
                path = STORIES_PATH
            }
            FeedType.FAVORITES -> {
                throw ApiException("feed data source : invalid feed type argument")
            }
        }
        val response = service.fetchFeed(path, tag, page)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                return RemoteResponseDto.Success(
                    RemotePagingResponseDto(
                        body.page,
                        body.hits.mapNotNull {
                            mapApiSearchItemToFeedPostItemDto(it)
                        },
                        body.hitsPerPage,
                    )
                )
            } else {
                throw ApiHttpResponseErrorException(
                    message = "empty body",
                    responseCode = response.code()
                )
            }
        } else {
            throw ApiHttpResponseErrorException(
                message = response.errorBody()?.string().orEmpty(),
                responseCode = response.code()
            )
        }
    }

}