package com.ofek.sample.domain.post

import com.ofek.sample.domain.base.BaseSuspendUseCase
import com.ofek.sample.domain.exceptions.InvalidUseCaseParamsException
import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto
import javax.inject.Inject

class FetchPostItemUseCase @Inject constructor(
    private val repository: PostRepository,
) :
    BaseSuspendUseCase<FetchPostItemUseCaseParams, RemoteResponseDto<PostItemDto>>() {

    override suspend fun run(params: FetchPostItemUseCaseParams?): RemoteResponseDto<PostItemDto> {
        return if (params != null && params.postId.isNotBlank()) {
            repository.fetchPost(params.postId)
        } else {
            RemoteResponseDto.Error(InvalidUseCaseParamsException(FetchPostItemUseCase::class.simpleName.orEmpty()))
        }
    }

}

class FetchPostItemUseCaseParams(
    val postId: String,
)