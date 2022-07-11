package com.ofek.sample.presentation.common

import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes resId: Int): String
    fun isSystemInDarkMode(): Boolean
}