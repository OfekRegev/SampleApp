package com.ofek.sample.data.exceptions

class ApiHttpResponseErrorException(
    message: String,
    responseCode: Int,
) : Exception(message) {
}