package com.ofek.sample.data.localdb.favorites

import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.post.PostItemDto

interface LocalDbFavoritesDataSource {

    suspend fun isPostInDatabase(postId: String): Boolean

    suspend fun getItems(skip: Int, count: Int): List<FeedPostItemDto>

    suspend fun saveItem(postItemDto: PostItemDto)

    suspend fun getItem(postId: String) : PostItemDto?
}