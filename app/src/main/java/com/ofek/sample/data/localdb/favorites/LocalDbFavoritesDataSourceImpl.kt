package com.ofek.sample.data.localdb.favorites

import com.ofek.sample.data.localdb.models.mapLocalDbPostToFeedPostItemDto
import com.ofek.sample.data.localdb.models.mapLocalDbPostToPostItemDto
import com.ofek.sample.data.localdb.models.mapPostItemDtoToLocalDbPost
import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.post.PostItemDto

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

    override suspend fun saveItem(postItemDto: PostItemDto) {
        val dbItem = mapPostItemDtoToLocalDbPost(postItemDto)
        dao.insertPostItem(dbItem)
    }

    override suspend fun getItem(postId: String): PostItemDto? {
        return if (isPostInDatabase(postId)) {
            mapLocalDbPostToPostItemDto(dao.getPost(postId))
        } else {
            null
        }
    }


}