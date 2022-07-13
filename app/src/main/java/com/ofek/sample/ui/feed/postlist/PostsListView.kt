package com.ofek.sample.ui.feed.postlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.ofek.sample.presentation.feed.models.UiPostListItemModel
import com.ofek.sample.ui.feed.postlist.theme.PostListItemColors
import com.ofek.sample.ui.feed.postlist.theme.PostListItemTypography
import com.ofek.sample.ui.feed.theme.FeedTheme
import com.ofek.sample.ui.widgets.ListLoadMoreLoader
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter

const val LOAD_MORE_THRESHOLD = 10

@Composable
fun PostListView(
    items: List<UiPostListItemModel>,
    canLoadMore: Boolean,
    colors: PostListItemColors,
    typography: PostListItemTypography,
    onRefresh: () -> Unit,
    onLoadMore: () -> Unit,
) {
    val listState = rememberLazyListState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = false)
    EndlessScrollListener(lazyListState = listState, threshold = LOAD_MORE_THRESHOLD, onLoadMore)
    SwipeRefresh(state = swipeRefreshState, onRefresh = onRefresh) {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(Sizes.ITEMS_PADDING.dp),
            contentPadding = PaddingValues(Sizes.ITEMS_PADDING.dp)
        ) {
            itemsIndexed(
                items = items,
                key = { index, it ->
                    // apparently could be some duplicates in the lists
                    it.itemId + index.toString()
                }
            ) { _, postItem ->
                PostListItem(model = postItem, colors = colors, typography = typography)
            }
            item() {
                if (canLoadMore) {
                    ListLoadMoreLoader(indicatorColor = FeedTheme.colors.listLoaderColor)
                }
            }
        }
    }
}

@OptIn(FlowPreview::class)
@Composable
fun EndlessScrollListener(
    lazyListState: LazyListState,
    threshold: Int,
    onLoadMore: () -> Unit,
) {
    val lastVisibleItem by remember {
        derivedStateOf {
            lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
        }
    }
    LaunchedEffect(lastVisibleItem) {
        snapshotFlow { lastVisibleItem }
            .filter { lastVisibleItem ->
                lastVisibleItem > lazyListState.layoutInfo.totalItemsCount - threshold && lazyListState.layoutInfo.totalItemsCount > 0
            }
            .debounce(300)
            .collect {
                onLoadMore.invoke()
            }
    }
}