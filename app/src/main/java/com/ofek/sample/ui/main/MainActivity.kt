package com.ofek.sample.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(
                LocalViewModelStoreOwner provides this
            ) {
                MainActivityRootView(
                    viewModelStoreOwner = this,
                    lifecycleOwner = this,
                    backPressedDispatcher = onBackPressedDispatcher
                )
            }
        }
    }
}