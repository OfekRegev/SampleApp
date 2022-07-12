package com.ofek.sample.presentation.main.toolbar

import androidx.annotation.DrawableRes
import com.ofek.sample.R

class ToolbarButtonModel(
    @DrawableRes val drawable: Int,
    val onClick: () -> Unit,
)

fun backToolbarButtonModel(
    onClick: () -> Unit,
): ToolbarButtonModel {
    return ToolbarButtonModel(
        drawable = R.drawable.back_arrow,
        onClick
    )
}

fun darkModeToolbarButton(
    onClick: () -> Unit,
): ToolbarButtonModel {
    return ToolbarButtonModel(
        R.drawable.icon_dark,
        onClick
    )
}

