package com.ofek.sample.presentation.main.toolbar

import androidx.annotation.DrawableRes
import com.ofek.sample.R

class ToolbarButtonModel(
    @DrawableRes val drawable: Int,
    val onClick: () -> Unit,
)

fun backToolbarButtonModel(
    darkMode: Boolean,
    onClick: () -> Unit,
): ToolbarButtonModel {
    return ToolbarButtonModel(
        drawable = if (darkMode) {
            R.drawable.arrow_back_dark
        } else {
            R.drawable.back_arrow_light
        },
        onClick
    )
}

