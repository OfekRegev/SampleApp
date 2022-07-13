package com.ofek.sample.domain.models.remote

sealed class RemoteResponseDto<T> {
    class Success<T>(
        val content: T
    ) : RemoteResponseDto<T>()

    class Error<T>(
        val exception: Exception,
    ) : RemoteResponseDto<T>()

}