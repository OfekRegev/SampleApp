package com.ofek.sample.presentation.errors

import androidx.annotation.StringRes
import com.ofek.sample.R

sealed class Error(
    @StringRes val messageRes: Int
)

object GeneralError : Error(R.string.general_error_message)
object NetworkError : Error(R.string.network_error_message)
