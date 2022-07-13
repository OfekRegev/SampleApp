package com.ofek.sample.domain.post

import com.ofek.sample.domain.base.BaseSuspendUseCase
import com.ofek.sample.domain.exceptions.InvalidUseCaseParamsException
import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto
import javax.inject.Inject

class AddPostToFavorites @Inject constructor(
    private val repository: PostRepository,
) :
    BaseSuspendUseCase<AddPostToFavoritesUseCaseParams, RemoteResponseDto<PostItemDto>>() {

    override suspend fun run(params: AddPostToFavoritesUseCaseParams?): RemoteResponseDto<PostItemDto> {
        return params?.let {
            repository.addPostToFavorites(params.post)
        }
            ?: RemoteResponseDto.Error(InvalidUseCaseParamsException(AddPostToFavorites::class.simpleName.orEmpty()))
    }

}


class AddPostToFavoritesUseCaseParams(
    val post: PostItemDto,
)