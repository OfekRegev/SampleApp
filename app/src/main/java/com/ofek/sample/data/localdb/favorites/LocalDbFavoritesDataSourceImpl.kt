package com.ofek.sample.data.localdb.favorites

import com.ofek.sample.data.localdb.models.mapLocalDbPostToFeedPostItemDto
import com.ofek.sample.data.localdb.models.mapLocalDbPostToPostItemDto
import com.ofek.sample.data.localdb.models.mapFeedPostItemDtoToLocalDbPost
import com.ofek.sample.domain.models.feed.FeedPostItemDto

class LocalDbFavoritesDataSourceImpl(
    private val dao: FavoritesPostsDao,
) : LocalDbFavoritesDataSource {

    override suspend fun isPostInDatabase(postId: String): Boolean {
        return dao.exists(postId)
    }

    override suspend fun getItems(skip: Int, count: Int): List<FeedPostItemDto> {
        return dao.getAllPosts().map {
            mapLocalDbPostToFeedPostItemDto(it)
        }
    }

    override suspend fun saveItem(postItemDto: FeedPostItemDto) {
        val dbItem = mapFeedPostItemDtoToLocalDbPost(postItemDto)
        dao.insertPostItem(dbItem)
    }

    override suspend fun getItem(postId: String): FeedPostItemDto? {
        return if (isPostInDatabase(postId)) {
            mapLocalDbPostToPostItemDto(dao.getPost(postId))
        } else {
            null
        }
    }

    override suspend fun updateItem(postItemDto: FeedPostItemDto) {
        val dbItem = mapFeedPostItemDtoToLocalDbPost(postItemDto)
        dao.updateItem(dbItem)
    }


}