package com.ofek.sample.domain.post

import com.ofek.sample.domain.base.BaseSuspendUseCase
import com.ofek.sample.domain.exceptions.InvalidUseCaseParamsException
import com.ofek.sample.domain.feed.FeedRepository
import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.post.PostItemDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto
import javax.inject.Inject

class AddPostToFavoritesUseCase @Inject constructor(
    private val repository: FeedRepository,
) : BaseSuspendUseCase<AddPostToFavoritesUseCaseParams, RemoteResponseDto<FeedPostItemDto>>() {

    override suspend fun run(params: AddPostToFavoritesUseCaseParams?): RemoteResponseDto<FeedPostItemDto> {
        return params?.let {
            repository.addPostToFavorites(params.post)
        } ?: RemoteResponseDto.Error(InvalidUseCaseParamsException(AddPostToFavoritesUseCase::class.simpleName.orEmpty()))
    }
}


class AddPostToFavoritesUseCaseParams(
    val post: FeedPostItemDto,
)