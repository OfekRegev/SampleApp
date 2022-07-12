package com.ofek.sample.presentation.common

import android.content.res.Configuration
import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDelegate

class ResourcesProviderImpl(
    private val resources: Resources,
) : ResourceProvider {
    override fun getString(@StringRes resId: Int): String {
        return resources.getString(resId)
    }

    override fun isApplicationInDarkMode(): Boolean {
        val nightModeFlags = AppCompatDelegate.getDefaultNightMode()
        Configuration.UI_MODE_NIGHT_MASK
        return when (nightModeFlags) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                true
            }
            else -> {
                false
            }
        }
    }

    override fun toggleDarkMode() {
        if (isApplicationInDarkMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }
}