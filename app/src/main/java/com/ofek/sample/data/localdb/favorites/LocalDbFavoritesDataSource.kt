package com.ofek.sample.data.localdb.favorites

import com.ofek.sample.domain.models.feed.FeedPostItemDto

interface LocalDbFavoritesDataSource {
    suspend fun isPostInDatabase(postId: String): Boolean

    suspend fun getItems(skip: Int, count: Int): List<FeedPostItemDto>

    suspend fun saveItem(postItemDto: FeedPostItemDto)

    suspend fun getItem(postId: String): FeedPostItemDto?
    suspend fun updateItem(postItemDto: FeedPostItemDto)
}