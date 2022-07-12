package com.ofek.sample.presentation.main.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.ofek.sample.presentation.navigation.Destination

data class BottomBarButtonModel(
    @DrawableRes val iconResId: Int,
    @StringRes val textResId: Int,
    val selected: Boolean,
    val destination: Destination,
    val onClick: (Destination) -> Unit,
)