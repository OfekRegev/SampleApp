package com.ofek.sample.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ofek.sample.R
import com.ofek.sample.presentation.common.ResourceProvider
import com.ofek.sample.presentation.common.ViewModelDispatchers
import com.ofek.sample.presentation.main.toolbar.ToolbarState
import com.ofek.sample.presentation.main.toolbar.backToolbarButtonModel
import com.ofek.sample.presentation.navigation.ArticlesDestination
import com.ofek.sample.presentation.navigation.NavigationManager
import com.ofek.sample.presentation.navigation.OnBoardingDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val navigationUseCase: NavigationManager,
    private val resourcesProvider: ResourceProvider,
    private val viewModelDispatchers: ViewModelDispatchers,
) : ViewModel() {

    init {
        navigationUseCase.navigationPathState()
            .onEach { newPath ->
                updateToolbarState(newPath)
                onNavigationPathChanged(newPath)
            }.launchIn(viewModelScope)
    }

    private val _toolbarState: MutableLiveData<ToolbarState?> = MutableLiveData(null)
    private val _navigationPath: MutableLiveData<String?> = MutableLiveData(null)


    fun getToolbarState() : LiveData<ToolbarState?> = _toolbarState
    fun getNavigationPath(): LiveData<String?> = _navigationPath

    private fun onNavigationPathChanged(newPath: String?) {
        viewModelScope.launch(context = viewModelDispatchers.mainDispatcher) {
            _navigationPath.value = newPath
        }
    }

    private fun updateToolbarState(currentPath: String?) {
        val pathSplit = currentPath.orEmpty().split('/')
        val lastRoute = pathSplit.last()
        val toolbarState = when {
            lastRoute.startsWith(ArticlesDestination.NAVIGATION_ROUTE) -> {
                ToolbarState(
                    titleResId = R.string.articles_title_text,
                    endButtons = listOf(
                        backToolbarButtonModel(
                            resourcesProvider.isSystemInDarkMode(),
                            ::onBackAction
                        )
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

    fun onBackAction() {
        viewModelScope.launch(context = viewModelDispatchers.asyncComputationDispatcher) {
            navigationUseCase.goBack()
        }
    }

    fun navigateToFirstScreen() {
        viewModelScope.launch(context = viewModelDispatchers.asyncComputationDispatcher) {
            navigationUseCase.navigateNext(
                OnBoardingDestination()
            )
        }
    }

}