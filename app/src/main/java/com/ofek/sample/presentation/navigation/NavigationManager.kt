package com.ofek.sample.presentation.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationManager {

    fun navigationPathState() : Flow<String?>

    fun navigateTo(destination: Destination)

    fun goBack()

    fun isCurrentDestination(
        path: String,
        destination: Destination
    ): Boolean

}