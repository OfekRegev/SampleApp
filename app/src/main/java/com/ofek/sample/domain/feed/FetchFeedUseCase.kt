package com.ofek.sample.domain.feed

import com.ofek.sample.domain.base.BaseSuspendUseCase
import com.ofek.sample.domain.exceptions.InvalidUseCaseParamsException
import com.ofek.sample.domain.models.feed.FeedPostItemDto
import com.ofek.sample.domain.models.remote.RemotePagingRequestDto
import com.ofek.sample.domain.models.remote.RemotePagingResponseDto
import com.ofek.sample.domain.models.remote.RemoteResponseDto
import javax.inject.Inject

class FetchFeedUseCase @Inject constructor(
    private val feedRepository: FeedRepository,
) :
    BaseSuspendUseCase<FetchFeedUseCase.FetchFeedUseCaseParams, RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>>>() {


    override suspend fun run(params: FetchFeedUseCaseParams?): RemoteResponseDto<RemotePagingResponseDto<List<FeedPostItemDto>>> {
        return params?.let {
            try {
                feedRepository.fetchFeed(it.remotePagingRequestDto, it.feedType)
            } catch (exception: Exception) {
                RemoteResponseDto.Error(exception = exception)
            }
        }
            ?: RemoteResponseDto.Error(InvalidUseCaseParamsException(FetchFeedUseCase::class.simpleName.orEmpty()))
    }

    class FetchFeedUseCaseParams(
        val remotePagingRequestDto: RemotePagingRequestDto,
        val feedType: FeedType,
    )
}