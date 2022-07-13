package com.ofek.sample.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ofek.sample.presentation.common.ViewModelDispatchers
import com.ofek.sample.presentation.navigation.ArticlesDestination

import com.ofek.sample.presentation.navigation.FavoritesDestination
import com.ofek.sample.presentation.navigation.NavigationManager
import com.ofek.sample.presentation.navigation.StoriesDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val viewModelDispatchers: ViewModelDispatchers,
) : ViewModel() {


    fun openArticles() {
        viewModelScope.launch(context = viewModelDispatchers.asyncComputationDispatcher) {
            navigationManager.navigateTo(ArticlesDestination())
        }
    }

    fun openStories() {
        viewModelScope.launch(context = viewModelDispatchers.asyncComputationDispatcher) {
            navigationManager.navigateTo(StoriesDestination())
        }
    }

    fun openFavorites() {
        viewModelScope.launch(context = viewModelDispatchers.asyncComputationDispatcher) {
            navigationManager.navigateTo(FavoritesDestination())
        }
    }


}