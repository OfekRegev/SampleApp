package com.ofek.sample.domain.models.remote

data class RemotePagingResponseDto<T>(
    val page: Int,
    val items: T,
    val itemsPerPage: Int,
    val canLoadMore: Boolean = false,
)