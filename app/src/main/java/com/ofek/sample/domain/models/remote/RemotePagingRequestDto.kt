package com.ofek.sample.domain.models.remote

/**
 * defines simple input parameters for paging request
 * page - the current page to fetch
 * itemsCount - optional number of items per page
 */
class RemotePagingRequestDto(
    val page: Int = 0,
    val itemsCount: Int? = null,
)