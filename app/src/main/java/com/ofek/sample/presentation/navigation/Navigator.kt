package com.ofek.sample.presentation.navigation

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * This class should handle navigation between the applications screens
 * The navigation channel should act as a single source of truth for the app navigation
 * Navigation scheme built as recommended in https://developer.android.com/jetpack/compose/navigation
 */
class Navigator : NavigationManager {

    private val channel = MutableStateFlow<String?>(null)

    override fun navigationPathState(): Flow<String?> = channel

    // navigate to the destination by adding the destination route to the current destination route
    override fun navigateTo(
        destination: Destination
    ) {
        val newPath = buildPath(channel.value, destination)
        if (newPath != channel.value) {
            channel.tryEmit(
                newPath
            )
        }
    }

    // navigate back by dropping the last route in the path
    override fun goBack() {
        channel.tryEmit(
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
    ): String {
        val pathSplit = currentPath.orEmpty().split('/')
        val newPathBuilder = StringBuilder()
        destination.ancestorRoutes.forEachIndexed { index, ancestor ->
            // preferably using the ancestor from the current path if it exists
            if (index < pathSplit.size && pathSplit[index].startsWith(ancestor)) {
                newPathBuilder.append(pathSplit[index])
            } else {
                // whenever it does not exist, the default ancestor is added and will have default values for arguments(empty arguments)
                newPathBuilder.append(ancestor)
            }
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
        return newPathBuilder.toString()
    }

    /**
     * Checking whether the current destination matches the argument destination.
     * using route.startsWith() because path might contains unknown params, i.e - destination?param=value
     */
    override fun isCurrentDestination(path: String, destination: Destination): Boolean {
        val pathSplit = path.split('/')
        // root destination would not have ancestors
        // path could start with root destination even when it's not the current destination, i.e rootdestination/otherdestination.
        return if (pathSplit.size <= 1 && destination.ancestorRoutes.isEmpty()) {
            path.startsWith(destination.route)
        } else {
            val lastRoute = pathSplit.lastOrNull()
            lastRoute.orEmpty().startsWith(destination.route)
        }
    }
}