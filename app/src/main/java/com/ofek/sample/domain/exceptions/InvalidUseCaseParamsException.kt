package com.ofek.sample.domain.exceptions

class InvalidUseCaseParamsException(
    useCaseClassName: String,
) : Exception(
    "Invalid parameters provided for $useCaseClassName"
) {
}