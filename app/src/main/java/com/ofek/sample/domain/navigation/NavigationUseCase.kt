package com.ofek.sample.domain.navigation

import com.ofek.sample.domain.base.BaseUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * This use case should handle navigation between the applications screens
 * The navigation channel should act as a single source of truth for the app navigation
 */
class NavigationUseCase @Inject constructor(
    private val channel: MutableStateFlow<String?>
) :
    BaseUseCase<BaseUseCase.None, StateFlow<String?>>() {


    override fun run(
        params: None?
    ): StateFlow<String?> {
        return channel
    }

    // navigate to the destination by adding the destination route to the current destination route
    suspend fun navigateNextDestination(
        destination: Destination
    ) {
        channel.emit(
            buildPath(channel.value, destination)
        )
    }

    // navigate back by dropping the last route in the path
    suspend fun goBack() {
        channel.emit(
            removeLastRouteFromPath(channel.value)
        )
    }

    private fun removeLastRouteFromPath(
        currentPath: String?
    ): String? {
        return currentPath?.let {
            val lastRouteStartIndex = currentPath.lastIndexOf('/')
            if (lastRouteStartIndex < 0) {
                currentPath
            } else {
                currentPath.substring(0, lastRouteStartIndex)
            }
        }
    }

    private fun buildPath(
        currentPath: String?,
        destination: Destination
    ): String? {
        return currentPath?.let {
            val newPathBuilder = StringBuilder(currentPath)
            // when current path is nor null or empty a '/' should be appended following the compose navigation scheme
            if (currentPath.isNullOrEmpty().not()) {
                newPathBuilder.append('/')
            }
            newPathBuilder.append(destination.route)
            if (destination.arguments.isNotEmpty()) {
                newPathBuilder.append("?")
                val arguments = destination.arguments.entries
                // adding arguments to the current route
                arguments.forEachIndexed { index, argument ->
                    newPathBuilder.append("${argument.key}=${argument.value}")
                    // following the compose navigation scheme the last argument should not be escaped with '&'
                    if (index < arguments.size - 1) {
                        newPathBuilder.append("&")
                    }
                }
            }
            newPathBuilder.toString()
        }
    }
}



