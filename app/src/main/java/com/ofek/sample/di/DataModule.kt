package com.ofek.sample.di

import android.content.Context
import androidx.room.Room
import com.ofek.sample.data.ApiConstants
import com.ofek.sample.data.LocalDbConstants
import com.ofek.sample.data.android.AndroidSystemManager
import com.ofek.sample.data.android.AndroidSystemManagerImpl
import com.ofek.sample.data.feed.api.FeedApiDataSource
import com.ofek.sample.data.feed.api.FeedApiDataSourceImpl
import com.ofek.sample.data.feed.api.FeedApiService
import com.ofek.sample.data.localdb.AppDatabase
import com.ofek.sample.data.localdb.favorites.FavoritesPostsDao
import com.ofek.sample.data.localdb.favorites.LocalDbFavoritesDataSource
import com.ofek.sample.data.localdb.favorites.LocalDbFavoritesDataSourceImpl
import com.ofek.sample.data.postitem.api.PostItemApiDataSource
import com.ofek.sample.data.postitem.api.PostItemApiDataSourceImpl
import com.ofek.sample.data.postitem.api.PostItemService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun retrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.HN_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun feedApiService(
        retrofit: Retrofit,
    ): FeedApiService {
        return retrofit.create(FeedApiService::class.java)
    }

    @Provides
    fun feedApiDataSource(
        service: FeedApiService,
    ): FeedApiDataSource {
        return FeedApiDataSourceImpl(service)
    }

    @Provides
    fun postItemService(
        retrofit: Retrofit
    ): PostItemService {
        return retrofit.create(PostItemService::class.java)
    }

    @Provides
    fun postItemApiDataSource(
        postItemService: PostItemService
    ) : PostItemApiDataSource {
        return PostItemApiDataSourceImpl(postItemService)
    }

    @Provides
    fun localDbFavoritesDataSource(
        favoritesPostsDao: FavoritesPostsDao
    ): LocalDbFavoritesDataSource {
        return LocalDbFavoritesDataSourceImpl(favoritesPostsDao)
    }

    @Provides
    fun androidSystemManager(
        @ApplicationContext context: Context
    ): AndroidSystemManager {
        return AndroidSystemManagerImpl(context)
    }

    @Provides
    fun favoritesDao(
        database: AppDatabase
    ): FavoritesPostsDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun appDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            LocalDbConstants.DATABASE_NAME
        ).build()
    }

}