package com.ofek.sample.ui.feed.postlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ofek.sample.R
import com.ofek.sample.presentation.feed.models.UiPostListItemModel
import com.ofek.sample.ui.feed.postlist.theme.PostListItemColors
import com.ofek.sample.ui.feed.postlist.theme.PostListItemTypography


@Composable
fun PostListItem(
    model: UiPostListItemModel,
    colors: PostListItemColors,
    typography: PostListItemTypography,
) {
    Card(
        modifier = Modifier.clickable(
            interactionSource = MutableInteractionSource(),
            indication = null,
        ) {
            model.onClick.invoke(model.itemId)
        }
    ) {
        Row(
            modifier = Modifier
                .background(color = colors.itemBackgroundColor)
                .fillMaxWidth()
                .height(Sizes.POST_LIST_ITEM_HEIGHT.dp)
                .padding(horizontal = Sizes.POST_LIST_ITEM_HORIZONTAL_PADDING.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = model.title,
                color = colors.titleTextColor,
                style = typography.titleTextStyle,
                maxLines = 1,
                modifier = Modifier.weight(85f),
                overflow = TextOverflow.Ellipsis
            )
            if (model.favorite) {
                Image(
                    painter = painterResource(id = R.drawable.icon_favorites),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(colors.favoriteIconColor)
                )
            }
            Text(
                text = model.counterText,
                color = colors.counterTextColor,
                style = typography.pointsTextStyle,
                modifier = Modifier.weight(15f),
                textAlign = TextAlign.End
            )
        }
    }
}
