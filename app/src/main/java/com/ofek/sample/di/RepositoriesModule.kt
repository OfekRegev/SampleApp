package com.ofek.sample.di

import com.ofek.sample.data.android.AndroidSystemManager
import com.ofek.sample.data.feed.FeedRepositoryImpl
import com.ofek.sample.data.feed.api.FeedApiDataSource
import com.ofek.sample.data.localdb.favorites.LocalDbFavoritesDataSource
import com.ofek.sample.data.postitem.PostItemRepositoryImpl
import com.ofek.sample.data.postitem.api.PostItemApiDataSource
import com.ofek.sample.domain.feed.FeedRepository
import com.ofek.sample.domain.post.PostItemRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    fun feedRepository(
        apiDataSource: FeedApiDataSource,
        localDbFeedDataSource: LocalDbFavoritesDataSource,
        androidSystemManager: AndroidSystemManager,
    ): FeedRepository {
        return FeedRepositoryImpl(apiDataSource, localDbFeedDataSource, androidSystemManager)
    }

    @Provides
    fun postItemRepository(
        postItemApiDataSource : PostItemApiDataSource
    ) : PostItemRepository {
        return PostItemRepositoryImpl(postItemApiDataSource)
    }
}