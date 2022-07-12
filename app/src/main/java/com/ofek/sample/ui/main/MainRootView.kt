package com.ofek.sample.ui.main

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ofek.sample.presentation.main.MainActivityViewModel
import com.ofek.sample.presentation.navigation.OnBoardingDestination
import com.ofek.sample.ui.main.theme.MainTheme
import com.ofek.sample.ui.main.toolbar.ToolbarView


@Composable
fun MainActivityRootView(
    mainViewModel: MainActivityViewModel = viewModel(),
    viewModelStoreOwner: ViewModelStoreOwner,
    lifecycleOwner: LifecycleOwner,
    backPressedDispatcher: OnBackPressedDispatcher,
) {
    val navigationPath = mainViewModel.getNavigationPath().observeAsState()
    val navController = rememberNavController()
    val toolbarState = mainViewModel.getToolbarState().observeAsState()
    val navigationPathReady by remember {
        derivedStateOf {
            navigationPath.value != null
        }
    }
    LaunchedEffect(navigationPath) {
        val currentDestination = navController.currentDestination
        val newPath = navigationPath.value
        if (newPath != null && currentDestination != null && currentDestination.route != newPath) {
            navController.navigate(route = newPath)
        }
    }
    lifecycleOwner.lifecycleScope.launchWhenCreated {
        mainViewModel.navigateToFirstScreen()
    }
    navController.setLifecycleOwner(lifecycleOwner)
    navController.setOnBackPressedDispatcher(backPressedDispatcher)
    BackHandler {
        mainViewModel.onBackAction()
    }
    if (navigationPathReady) {
        val startDestination = mainViewModel.getNavigationPath().value
        MainTheme {
            Scaffold(
                scaffoldState = rememberScaffoldState(),
                topBar = { ToolbarView(toolbarState.value) },
                modifier = Modifier.background(MainTheme.colors.backgroundColor)
            ) { paddingValues ->
                NavHost(
                    navController = navController,
                    startDestination = startDestination.orEmpty(),
                    modifier = Modifier.padding(paddingValues)
                ) {
                    composable(
                        route = OnBoardingDestination.DECLARATION_ROUTE
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
    }

}