package com.ofek.sample.ui.main.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ofek.sample.presentation.main.toolbar.ToolbarButtonModel
import com.ofek.sample.presentation.main.toolbar.ToolbarState
import com.ofek.sample.ui.main.toolbar.theme.ToolbarTheme


@Composable
fun ToolbarView(
    toolbarState: ToolbarState?
) {
    toolbarState?.let {
        ToolbarTheme {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .background(ToolbarTheme.colors.toolbarBackgroundColor)
                    .height(Sizes.TOOLBAR_SIZE.dp)
            ) {
                // start buttons
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f)
                ) {
                    toolbarState.startButtons.forEach {
                        ToolbarButton(toolbarButtonModel = it)
                    }
                }
                Spacer(modifier = Modifier.width(Sizes.TOOLBAR_TITLE_SPACING.dp))
                Text(
                    text = stringResource(id = toolbarState.titleResId),
                    style = ToolbarTheme.typography.toolbarTitleTextStyle,
                    color = ToolbarTheme.colors.toolbarTitleTextColor
                )
                Spacer(modifier = Modifier.width(Sizes.TOOLBAR_TITLE_SPACING.dp))
                // end buttons
                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    toolbarState.endButtons.forEach {
                        ToolbarButton(toolbarButtonModel = it)
                    }
                }
            }
        }
    }
}

@Composable
private fun ToolbarButton(
    toolbarButtonModel: ToolbarButtonModel
) {
    Image(
        painter = painterResource(id = toolbarButtonModel.drawable),
        contentDescription = null,
        modifier = Modifier
            .fillMaxHeight()
            .aspectRatio(1f)
            .padding(Sizes.TOOLBAR_BUTTON_ICON_PADING.dp)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                toolbarButtonModel.onClick.invoke()
            },
        colorFilter = ColorFilter.tint(ToolbarTheme.colors.toolbarButtonColor)
    )
}