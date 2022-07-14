package com.ofek.sample.ui.feed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ofek.sample.domain.feed.FeedType
import com.ofek.sample.presentation.feed.FeedViewModel
import com.ofek.sample.ui.feed.postlist.PostListView
import com.ofek.sample.ui.feed.theme.FeedTheme
import com.ofek.sample.ui.widgets.ErrorHandler


@Composable
fun FeedRootView(
    factory: FeedViewModel.FeedViewModelFactory,
    feedType: FeedType,
    feedViewModel: FeedViewModel = viewModel(
        factory = FeedViewModel.provideFactory(
            factory,
            feedType
        ), key = feedType.name
    ),
) {
    LocalLifecycleOwner.current.lifecycleScope.launchWhenCreated {
        feedViewModel.onScreenCreated()
    }
    val feedState = feedViewModel.feedState().observeAsState()
    val state by remember {
        feedState
    }
    FeedTheme {
        PostListView(
            items = state?.items.orEmpty(),
            canLoadMore = state?.canLoadMore ?: false,
            colors = FeedTheme.colors.postListItemColors,
            typography = FeedTheme.typography.postListItemTypography,
            onRefresh = { feedViewModel.refresh() },
            error = feedViewModel.getErrorState().value != null
        ) {
            feedViewModel.onLoadMore()
        }
        ErrorHandler(errorState = feedViewModel.getErrorState().observeAsState())
    }
}

