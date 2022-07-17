package com.ofek.sample.presentation.navigation

import com.ofek.sample.BuildConfig
import com.ofek.sample.extensions.norNullAndEmpty
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
        if (newPath.norNullAndEmpty() && newPath != channel.value) {
            channel.tryEmit(
                newPath
            )
        }
    }

    // navigate back by dropping the last route in the path
    override fun goBack() {
        val newPath = removeLastRouteFromPath(channel.value)
        if (newPath.norNullAndEmpty()) {
            channel.tryEmit(
                newPath
            )
        }
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
        val pathSplit = currentPath.orEmpty().split('/')
        val newPathBuilder = StringBuilder()
        // using ancestors is necessary to define whether the new route should replace or append to the current route
        // for example navigation from onboarding/articles to stories would be a replacement - i.e onboarding/stories, and not appending i.e onboarding/articles/stories
        destination.ancestors.forEachIndexed { index, ancestor ->
            if (index < pathSplit.size && pathSplit[index].startsWith(ancestor)) {
                // taking the route from current path as ancestor could be dynamic - i.e ancestor1/{ancestor2}/destination
                newPathBuilder.append(pathSplit[index])
            } else {
                // this is an edge case, this is normally cannot happen under any circumstances, only when the destinations didn't set up ancestors correctly
                // thus on debug mode throwing an indicative exception is required, otherwise just cancel the navigation by returning null path
                if (BuildConfig.DEBUG) {
                    throw IllegalStateException("path doesn't comply to the ancestors, please review destinations declaration, currentPath: $currentPath, next destination: $destination")
                } else {
                    return null
                }
            }
            newPathBuilder.append('/')
        }
        newPathBuilder.append(buildFullRouteForDestination(destination))
        return newPathBuilder.toString()
    }

    /**
     * Checking whether the current destination matches the argument destination.
     */
    override fun isCurrentDestination(path: String, destination: Destination): Boolean {
        val pathSplit = path.split('/')
        val lastRoute = pathSplit.lastOrNull()
        val destinationRoute = buildFullRouteForDestination(destination)
        //using lastRoute.startsWith() because path might contains unknown params, i.e - post?postId=unknownId
        return lastRoute.orEmpty().startsWith(destinationRoute)
    }

    private fun buildFullRouteForDestination(destination: Destination): String {
        val routeBuilder = StringBuilder()
        routeBuilder.append(destination.route)
        if (destination.arguments.isNotEmpty()) {
            routeBuilder.append("?")
            val arguments = destination.arguments.entries
            // adding arguments to the current route
            arguments.forEachIndexed { index, argument ->
                routeBuilder.append("${argument.key}=${argument.value}")
                // following the compose navigation scheme the last argument should not be escaped with '&'
                if (index < arguments.size - 1) {
                    routeBuilder.append("&")
                }
            }
        }
        return routeBuilder.toString()
    }
}