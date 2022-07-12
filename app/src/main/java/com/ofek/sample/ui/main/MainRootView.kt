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
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ofek.sample.presentation.main.MainActivityViewModel
import com.ofek.sample.presentation.navigation.ArticlesDestination
import com.ofek.sample.presentation.navigation.FavoritesDestination
import com.ofek.sample.presentation.navigation.OnBoardingDestination
import com.ofek.sample.presentation.navigation.StoriesDestination
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
                        route = ArticlesDestination.DECLARATION_ROUTE
                    ) {
                        CompositionLocalProvider(
                            LocalViewModelStoreOwner provides viewModelStoreOwner
                        ) {
                            Box(
                                modifier = Modifier.fillMaxHeight(

                                )
                            )
                        }
                    }
                    composable(
                        route = StoriesDestination.DECLARATION_ROUTE
                    ) {
                        CompositionLocalProvider(
                            LocalViewModelStoreOwner provides viewModelStoreOwner
                        ) {
                            Box(
                                modifier = Modifier.fillMaxHeight(

                                )
                            )
                        }
                    }
                    composable(
                        route = FavoritesDestination.DECLARATION_ROUTE
                    ) {
                        CompositionLocalProvider(
                            LocalViewModelStoreOwner provides viewModelStoreOwner
                        ) {
                            Box(
                                modifier = Modifier.fillMaxHeight(

                                )
                            )
                        }
                    }

                }
            }
        }
    }

}