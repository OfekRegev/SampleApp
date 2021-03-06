package com.ofek.sample.data.feed

import com.ofek.sample.data.android.AndroidSystemManager
import com.ofek.sample.data.exceptions.NetworkUnavailableException
import com.ofek.sample.data.feed.api.FeedApiDataSource
import com.ofek.sample.data.localdb.favorites.LocalDbFavoritesDataSource
import com.ofek.sample.domain.feed.FeedRepository
import com.ofek.sample.domain.feed.FeedType
import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.remote.RemotePagingRequestDto
import com.ofek.sample.domain.models.remote.RemotePagingResponseDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

class FeedRepositoryImpl(
    private val apiDataSource: FeedApiDataSource,
    private val localDbDataSource: LocalDbFavoritesDataSource,
    private val androidSystemManager: AndroidSystemManager,
) : FeedRepository {

    companion object {
        private const val DEFAULT_PAGING_ITEMS_COUNT = 20
    }

    @Throws(Exception::class)
    override suspend fun fetchFeed(
        remotePagingRequest: RemotePagingRequestDto,
        feedType: FeedType
    ): RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>> {
        return when (feedType) {
            FeedType.ARTICLES, FeedType.STORIES -> {
                fetchFeedFromApi(remotePagingRequest, feedType)
            }
            FeedType.FAVORITES -> {
                fetchFeedFromLocalDb(remotePagingRequest)
            }
        }
    }

    override suspend fun addPostToFavorites(post: FeedPostItemDto): RemoteResponseDto<FeedPostItemDto> {
        return if (localDbDataSource.isPostInDatabase(post.postId)) {
            val currentPost = localDbDataSource.getItem(post.postId)
            currentPost?.let {
                val updatedPost = currentPost.copy(
                    views = currentPost.views + 1
                )
                localDbDataSource.updateItem(
                    updatedPost
                )
                RemoteResponseDto.Success(updatedPost)
            } ?: RemoteResponseDto.Error(IllegalStateException("database error"))
        } else {
            val updatedItem = post.copy(
                views = post.views + 1,
                favorite = true
            )
            localDbDataSource.saveItem(updatedItem)
            RemoteResponseDto.Success(updatedItem)
        }
    }

    private suspend fun fetchFeedFromLocalDb(
        remotePagingRequest: RemotePagingRequestDto,
    ): RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>> {
        val itemsCount = remotePagingRequest.itemsCount ?: DEFAULT_PAGING_ITEMS_COUNT
        val page = remotePagingRequest.page
        val feedItems = localDbDataSource.getItems(
            itemsCount.times(page),
            itemsCount
        )
        val canLoadMore = feedItems.count() == itemsCount
        return RemoteResponseDto.Success(
            RemotePagingResponseDto(
                page,
                feedItems,
                itemsCount,
                canLoadMore
            )
        )
    }

    private suspend fun fetchFeedFromApi(
        remotePagingRequest: RemotePagingRequestDto,
        feedType: FeedType
    ): RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>> {
        if (androidSystemManager.isNetworkAvailable().not()) {
            return RemoteResponseDto.Error(NetworkUnavailableException())
        }
        val response = try {
            apiDataSource.fetchFeed(remotePagingRequest.page, feedType)
        } catch (exception: Exception) {
            RemoteResponseDto.Error(exception = exception)
        }
        return if (response is RemoteResponseDto.Success) {
            val canLoadMore = response.content.items.count() == response.content.itemsPerPage
            val items = response.content.items.map {
                val isInFavorites = localDbDataSource.isPostInDatabase(it.postId)
                it.copy(
                    favorite = isInFavorites
                )
            }
            RemoteResponseDto.Success(
                response.content.copy(
                    items = items,
                    canLoadMore = canLoadMore
                )
            )
        } else {
            response
        }
    }

}