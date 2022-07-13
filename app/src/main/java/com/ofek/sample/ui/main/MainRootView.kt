package com.ofek.sample.ui.main

import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ofek.sample.presentation.feed.FeedViewModel
import com.ofek.sample.presentation.main.MainActivityViewModel
import com.ofek.sample.presentation.navigation.FeedDestination
import com.ofek.sample.presentation.navigation.OnBoardingDestination
import com.ofek.sample.ui.feed.FeedRootView
import com.ofek.sample.ui.main.bottombar.BottomBarView
import com.ofek.sample.ui.main.theme.MainTheme
import com.ofek.sample.ui.main.toolbar.ToolbarView
import com.ofek.sample.ui.onboarding.OnBoardingRootView


@Composable
fun MainActivityRootView(
    mainViewModel: MainActivityViewModel = viewModel(),
    viewModelStoreOwner: ViewModelStoreOwner,
    lifecycleOwner: LifecycleOwner,
    backPressedDispatcher: OnBackPressedDispatcher,
    feedViewModelFactory: FeedViewModel.FeedViewModelFactory
) {
    val navigationPathState = mainViewModel.getNavigationPath()
    val navController = rememberNavController()
    val toolbarState = mainViewModel.getToolbarState().observeAsState()
    val bottomBarState = mainViewModel.getBottomBarState().observeAsState()
    DisposableEffect(navigationPathState) {
        val pathObserver = Observer<String?> { newPath ->
            val currentDestination = navController.currentDestination
            if (newPath != null && currentDestination != null && currentDestination.route != newPath) {
                navController.navigate(route = newPath)
            }
        }
        navigationPathState.observeForever(pathObserver)
        onDispose {
            navigationPathState.removeObserver(pathObserver)
        }
    }

    navController.setLifecycleOwner(lifecycleOwner)
    navController.setOnBackPressedDispatcher(backPressedDispatcher)
    BackHandler {
        mainViewModel.onBackAction()
    }
    LocalLifecycleOwner.current.lifecycleScope.launchWhenCreated {
        mainViewModel.onScreenCreated()
    }
    MainTheme {
        Scaffold(
            scaffoldState = rememberScaffoldState(),
            topBar = { ToolbarView(toolbarState.value) },
            bottomBar = { BottomBarView(bottomBarState = bottomBarState.value) },
        ) { paddingValues ->
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(MainTheme.colors.backgroundColor)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = OnBoardingDestination().route,
                    modifier = Modifier
                        .padding(paddingValues)
                ) {
                    composable(
                        route = OnBoardingDestination.DECLARATION_ROUTE
                    ) {
                        CompositionLocalProvider(
                            LocalViewModelStoreOwner provides viewModelStoreOwner
                        ) {
                            OnBoardingRootView()
                        }
                    }

                    composable(
                        route = FeedDestination.DECLARATION_ROUTE,
                        arguments = listOf(
                            navArgument(FeedDestination.FEED_TYPE_ARGUMENT_KEY) {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val feedTypeParam =
                            it.arguments?.getString(FeedDestination.FEED_TYPE_ARGUMENT_KEY)
                                .orEmpty()
                        CompositionLocalProvider(
                            LocalViewModelStoreOwner provides viewModelStoreOwner
                        ) {
                            FeedRootView(
                                factory = feedViewModelFactory,
                                FeedDestination.feedType(feedTypeParam)
                            )
                        }
                    }

                }
            }
        }
    }

}