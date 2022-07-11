package com.ofek.sample.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ofek.sample.domain.navigation.OnBoardingDestination
import com.ofek.sample.presentation.main.MainActivityViewModel
import com.ofek.sample.ui.main.toolbar.ToolbarView


@Composable
fun MainActivityRootView(
    mainViewModel: MainActivityViewModel = viewModel(),
    viewModelStoreOwner: ViewModelStoreOwner,
) {
    val navController = rememberNavController()
    val toolbarState = mainViewModel.toolbarState.observeAsState()
    val startDestination = mainViewModel.getNavigationPath().value
    val navigationPath = mainViewModel.getNavigationPath().observeAsState()
    LaunchedEffect(navigationPath) {
        val currentDestination = navController.currentDestination
        val newPath = navigationPath.value
        if (newPath != null && currentDestination != null && currentDestination.route != newPath) {
            navController.navigate(route = newPath)
        }
    }
    Scaffold(
        scaffoldState = rememberScaffoldState(),
        topBar = { ToolbarView(toolbarState.value) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = startDestination.orEmpty(),
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(
                route = OnBoardingDestination.DECLARATION_DESTINATION_ROUTE
            ) {
                CompositionLocalProvider(
                    LocalViewModelStoreOwner provides viewModelStoreOwner
                ) {
                    Box(modifier = Modifier.fillMaxHeight())
                }
            }
        }
    }

}