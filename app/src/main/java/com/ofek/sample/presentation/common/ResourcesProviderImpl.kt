package com.ofek.sample.presentation.common

import android.content.res.Configuration
import android.content.res.Resources
import androidx.annotation.StringRes

class ResourcesProviderImpl(
    private val resources: Resources,
) : ResourceProvider {
    override fun getString(@StringRes resId: Int): String {
        return resources.getString(resId)
    }

    override fun isSystemInDarkMode(): Boolean {
        val nightModeFlags = resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK
        return when (nightModeFlags) {
            Configuration.UI_MODE_NIGHT_YES -> {
                true
            }
            else -> {
                false
            }
        }
    }
}