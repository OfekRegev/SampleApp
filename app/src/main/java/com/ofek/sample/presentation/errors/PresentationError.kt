package com.ofek.sample.presentation.errors

import androidx.annotation.StringRes
import com.ofek.sample.R
import com.ofek.sample.data.exceptions.ApiException
import com.ofek.sample.data.exceptions.NetworkUnavailableException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

sealed class PresentationError(
    @StringRes val messageRes: Int
)

object GeneralError : PresentationError(R.string.general_error_message)
object NetworkError : PresentationError(R.string.network_error_message)

fun presentationErrorFromException(
    exception: Exception,
): PresentationError {
    return if (exception is NetworkUnavailableException ||
        exception is ApiException || exception is UnknownHostException || exception is SocketTimeoutException
    ) {
        NetworkError
    } else {
        GeneralError
    }
}
