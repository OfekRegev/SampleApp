package com.ofek.sample.data.postitem.api

import com.ofek.sample.data.exceptions.ApiHttpResponseErrorException
import com.ofek.sample.data.postitem.api.models.mapApiPostItemToPostItemDto
import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto

class PostItemApiDataSourceImpl(
    private val postItemService: PostItemService
) : PostItemApiDataSource {
    override suspend fun fetchPostItem(postId: String): RemoteResponseDto<PostItemDto> {
        val response = postItemService.fetchPostItem(postId)
        return if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                RemoteResponseDto.Success(
                    mapApiPostItemToPostItemDto(body)
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