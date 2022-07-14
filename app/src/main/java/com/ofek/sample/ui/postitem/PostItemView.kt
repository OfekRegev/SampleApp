package com.ofek.sample.ui.postitem

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ofek.sample.R
import com.ofek.sample.presentation.postitem.PostItemViewModel
import com.ofek.sample.ui.postitem.theme.PostItemTheme


@Composable
fun PostItemView(
    postItemId: String,
    postItemViewModelFactory: PostItemViewModel.PostItemViewModelFactory,
    postItemViewModel: PostItemViewModel = viewModel(
        factory = PostItemViewModel.provideFactory(
            postItemViewModelFactory,
            postItemId
        ), key = postItemId
    )
) {
    LocalLifecycleOwner.current.lifecycleScope.launchWhenCreated {
        postItemViewModel.onScreenCreated()
    }
    PostItemTheme {

        Column(
            modifier = Modifier
                .padding(Sizes.ROOT_PADDING.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center
        ) {
            val postItemState = postItemViewModel.postItemState().observeAsState()
            val postItemStateValue by remember {
                postItemState
            }
            postItemStateValue?.let { state ->
                if (state.loading) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(Sizes.PROGRESS_BAR_SIZE.dp),
                            color = PostItemTheme.colors.progressBarColor
                        )
                    }
                } else {
                    state.postItemModel?.let { post ->
                        val titleText =
                            "${stringResource(id = R.string.title)}: ${post.title}"
                        Text(
                            text = titleText,
                            color = PostItemTheme.colors.titleTextColor,
                            style = PostItemTheme.typography.titleText
                        )
                        Spacer(modifier = Modifier.height(Sizes.TEXTS_SPACING.dp))
                        val authorText =
                            "${stringResource(id = R.string.author)}: ${post.author}"
                        Text(
                            text = authorText,
                            color = PostItemTheme.colors.authorTextColor,
                            style = PostItemTheme.typography.titleText
                        )
                        Spacer(modifier = Modifier.height(Sizes.TEXTS_SPACING.dp))
                        val pointsText =
                            "${stringResource(id = R.string.points)}: ${post.points}"
                        Text(
                            text = pointsText,
                            color = PostItemTheme.colors.pointsCountTextColor,
                            style = PostItemTheme.typography.titleText
                        )
                    }
                }
            }
        }
    }
}