package com.ofek.sample.data.postitem.api

import com.ofek.sample.data.postitem.api.models.ApiPostItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostItemService {

    @GET("items/{id}")
    suspend fun fetchPostItem(@Path("id") postId: String) : Response<ApiPostItem>

}