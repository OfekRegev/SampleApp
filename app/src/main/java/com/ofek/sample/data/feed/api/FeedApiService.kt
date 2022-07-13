package com.ofek.sample.data.feed.api

import com.ofek.sample.data.feed.api.models.ApiSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FeedApiService {

    @GET("{searchpath}")
    suspend fun fetchFeed(
        @Path("searchpath") searchPath: String,
        @Query("tags") tag: String,
        @Query("page") page: Int,
    ): Response<ApiSearchResponse>
}