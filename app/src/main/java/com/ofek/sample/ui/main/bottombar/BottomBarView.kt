package com.ofek.sample.ui.main.bottombar

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ofek.sample.presentation.main.bottombar.BottomBarButtonModel
import com.ofek.sample.presentation.main.bottombar.BottomBarState
import com.ofek.sample.ui.main.bottombar.theme.BottomBarTheme

@Composable
fun BottomBarView(
    bottomBarState: BottomBarState?,
) {
    bottomBarState?.let {
        BottomBarTheme {
            Row(
                modifier = Modifier
                    .height(Sizes.BOTTOM_BAR_HEIGHT.dp)
                    .padding(horizontal = Sizes.BOTTOM_BAR_HORIZONTAL_PADDING.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                BottomBarButton(it.articlesButtonModel)
                BottomBarButton(it.storiesButtonModel)
                BottomBarButton(it.favoritesButtonModel)
            }
        }
    }
}

@Composable
fun BottomBarButton(
    bottomBarButtonModel: BottomBarButtonModel,
) {
    val iconColor = if (bottomBarButtonModel.selected) {
        BottomBarTheme.colors.bottomBarButtonSelectedColor
    } else {
        BottomBarTheme.colors.bottomBarButtonUnselectedColor
    }
    val textColor = if (bottomBarButtonModel.selected) {
        BottomBarTheme.colors.bottomBarButtonSelectedTextColor
    } else {
        BottomBarTheme.colors.bottomBarButtonUnselectedTextColor
    }

    Column(
        Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
            ) {
                bottomBarButtonModel.onClick.invoke(bottomBarButtonModel.destination)
            }
            .padding(Sizes.BOTTOM_BAR_BUTTON_PADDING.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = bottomBarButtonModel.iconResId),
            contentDescription = null,
            colorFilter = ColorFilter.tint(iconColor, BlendMode.SrcAtop)
        )
        Text(
            text = stringResource(id = bottomBarButtonModel.textResId),
            color = textColor,
            style = BottomBarTheme.typography.bottomBarButtonTextStyle
        )
    }
}