package com.ofek.sample.ui.postlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ofek.sample.presentation.postlist.UiPostListItemModel
import com.ofek.sample.ui.postlist.theme.PostListItemColors
import com.ofek.sample.ui.postlist.theme.PostListItemTypography


@Composable
fun PostListItem(
    model: UiPostListItemModel,
    colors: PostListItemColors,
    typography: PostListItemTypography,
) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Sizes.POST_LIST_ITEM_HEIGHT.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = model.title,
                color = colors.titleTextColor,
                style = typography.titleTextStyle,
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .defaultMinSize(minWidth = Sizes.MIN_SPACE_BETWEEN_TEXTS.dp)
            )
            Text(
                text = model.counterText,
                color = colors.counterTextColor,
                style = typography.pointsTextStyle,
            )
        }
    }
}