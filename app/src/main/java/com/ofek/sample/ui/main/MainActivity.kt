package com.ofek.sample.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import com.ofek.sample.presentation.feed.FeedViewModel
import com.ofek.sample.presentation.postitem.PostItemViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var feedViewModelFactory: FeedViewModel.FeedViewModelFactory

    @Inject
    lateinit var postItemViewModelFactory: PostItemViewModel.PostItemViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalViewModelStoreOwner provides this
            ) {
                // using activity as ViewModelStoreOwner will allow ViewModels to survive configuration changes
                MainActivityRootView(
                    viewModelStoreOwner = this,
                    lifecycleOwner = this,
                    backPressedDispatcher = onBackPressedDispatcher,
                    feedViewModelFactory = feedViewModelFactory,
                    postItemViewModelFactory = postItemViewModelFactory
                )
            }
        }
    }
}