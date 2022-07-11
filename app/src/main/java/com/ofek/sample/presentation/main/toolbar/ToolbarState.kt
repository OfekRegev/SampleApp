package com.ofek.sample.presentation.main.toolbar

import androidx.annotation.StringRes

class ToolbarState(
    @StringRes val titleResId: Int,
    val endButtons: List<ToolbarButtonModel> = emptyList(),
    val startButtons: List<ToolbarButtonModel> = emptyList(),
)
