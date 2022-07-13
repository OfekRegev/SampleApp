package com.ofek.sample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ofek.sample.R
import com.ofek.sample.presentation.common.ResourceProvider
import com.ofek.sample.presentation.common.ViewModelDispatchers
import com.ofek.sample.presentation.main.bottombar.BottomBarButtonModel
import com.ofek.sample.presentation.main.bottombar.BottomBarState
import com.ofek.sample.presentation.main.toolbar.ToolbarState
import com.ofek.sample.presentation.main.toolbar.backToolbarButtonModel
import com.ofek.sample.presentation.main.toolbar.darkModeToolbarButton
import com.ofek.sample.presentation.navigation.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val resourcesProvider: ResourceProvider,
    private val viewModelDispatchers: ViewModelDispatchers,
) : ViewModel() {

    init {
        navigationManager.navigationPathState()
            .onEach { newPath ->
                updateToolbarState(newPath)
                updateBottomBarState(newPath)
                onNavigationPathChanged(newPath)
            }
            .launchIn(viewModelScope)
    }

    private val _toolbarState: MutableLiveData<ToolbarState?> = MutableLiveData(null)
    private val _bottomBarState: MutableLiveData<BottomBarState?> = MutableLiveData(null)
    private val _navigationPath: MutableLiveData<String?> = MutableLiveData(null)


    fun getToolbarState(): LiveData<ToolbarState?> = _toolbarState
    fun getNavigationPath(): LiveData<String?> = _navigationPath
    fun getBottomBarState(): LiveData<BottomBarState?> = _bottomBarState

    private fun onNavigationPathChanged(newPath: String?) {
        viewModelScope.launch(context = viewModelDispatchers.mainDispatcher) {
            _navigationPath.value = newPath
        }
    }

    private fun updateToolbarState(currentPath: String?) {
        val toolbarState = when {
            navigationManager.isCurrentDestination(
                currentPath.orEmpty(),
                ArticlesDestination()
            ) -> {
                ToolbarState(
                    titleResId = R.string.articles_text,
                    startButtons = listOf(
                        backToolbarButtonModel(
                            ::onBackAction
                        )
                    ),
                    endButtons = listOf(
                        darkModeToolbarButton(
                        ) {
                            resourcesProvider.toggleDarkMode()
                        }
                    )
                )
            }
            navigationManager.isCurrentDestination(currentPath.orEmpty(), StoriesDestination()) -> {
                ToolbarState(
                    titleResId = R.string.stories_text,
                    startButtons = listOf(
                        backToolbarButtonModel(
                            ::onBackAction
                        )
                    ),
                    endButtons = listOf(
                        darkModeToolbarButton() {
                            resourcesProvider.toggleDarkMode()
                        }
                    )
                )
            }
            navigationManager.isCurrentDestination(
                currentPath.orEmpty(),
                FavoritesDestination()
            ) -> {
                ToolbarState(
                    titleResId = R.string.favorites_text,
                    startButtons = listOf(
                        backToolbarButtonModel(
                            ::onBackAction
                        )
                    ),
                    endButtons = listOf(
                        darkModeToolbarButton() {
                            resourcesProvider.toggleDarkMode()
                        }
                    )
                )
            }
            else -> {
                null
            }
        }
        viewModelScope.launch(context = viewModelDispatchers.mainDispatcher) {
            _toolbarState.value = toolbarState
        }
    }

    private fun updateBottomBarState(newPath: String?) {
        if (newPath == null) {
            return
        }
        if (navigationManager.isCurrentDestination(
                newPath,
                OnBoardingDestination()
            )
        ) {
            _bottomBarState.value = null
        } else {
            val articlesButtonModel = BottomBarButtonModel(
                textResId = R.string.articles_text,
                iconResId = R.drawable.icon_articles,
                selected = navigationManager.isCurrentDestination(
                    newPath.orEmpty(),
                    ArticlesDestination()
                ),
                destination = ArticlesDestination(),
                onClick = ::navigateToDestination
            )
            val storiesButtonModel = BottomBarButtonModel(
                textResId = R.string.stories_text,
                iconResId = R.drawable.icon_stories,
                selected = navigationManager.isCurrentDestination(
                    newPath.orEmpty(),
                    StoriesDestination()
                ),
                destination = StoriesDestination(),
                onClick = ::navigateToDestination
            )
            val favoritesButtonModel = BottomBarButtonModel(
                textResId = R.string.favorites_text,
                iconResId = R.drawable.icon_favorites,
                selected = navigationManager.isCurrentDestination(
                    newPath.orEmpty(),
                    FavoritesDestination()
                ),
                destination = FavoritesDestination(),
                onClick = ::navigateToDestination
            )
            _bottomBarState.value = BottomBarState(
                articlesButtonModel = articlesButtonModel,
                storiesButtonModel = storiesButtonModel,
                favoritesButtonModel = favoritesButtonModel,
            )
        }
    }

    private fun navigateToDestination(destination: Destination) {
        viewModelScope.launch(context = viewModelDispatchers.asyncComputationDispatcher) {
            navigationManager.navigateTo(
                destination
            )
        }
    }

    fun onBackAction() {
        viewModelScope.launch(context = viewModelDispatchers.asyncComputationDispatcher) {
            navigationManager.goBack()
        }
    }

    fun onScreenCreated() {
        // navigate to the first screen only when screen has created for the first time
        // avoid changing navigation on recreation(i.e configuration changes)
        if (_navigationPath.value == null) {
            viewModelScope.launch(viewModelDispatchers.asyncComputationDispatcher) {
                navigateToDestination(OnBoardingDestination())
            }
        }
    }

}