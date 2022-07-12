package com.ofek.sample.presentation.onboarding

import androidx.lifecycle.ViewModel
import com.ofek.sample.presentation.navigation.NavigationManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
) : ViewModel() {


}