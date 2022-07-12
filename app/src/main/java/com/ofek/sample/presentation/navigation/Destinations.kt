package com.ofek.sample.presentation.navigation

/**
 * route - the destination navigation route
 * arguments - optional arguments adding to route
 * ancestorRoutes - the routes that should come before this destination
 */
sealed class Destination(
    val route: String,
    val arguments: Map<String, String> = emptyMap(),
    val ancestorRoutes: List<String>
)

class OnBoardingDestination() : Destination(
    route = NAVIGATION_ROUTE,
    ancestorRoutes = emptyList()
) {
    companion object {
        const val NAVIGATION_ROUTE = "onborading"
        const val DECLARATION_ROUTE = NAVIGATION_ROUTE
    }
}

class StoriesDestination : Destination(
    route = NAVIGATION_ROUTE,
    ancestorRoutes = listOf(
        OnBoardingDestination.NAVIGATION_ROUTE,
    )
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/${NAVIGATION_ROUTE}"
    }
}

class ArticlesDestination : Destination(
    route = NAVIGATION_ROUTE,
    ancestorRoutes = listOf(
        OnBoardingDestination.NAVIGATION_ROUTE,
    )
) {
    companion object {
        const val NAVIGATION_ROUTE = "articles"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/${NAVIGATION_ROUTE}"
    }
}

class FavoritesDestination : Destination(
    route = NAVIGATION_ROUTE,
    ancestorRoutes = listOf(
        OnBoardingDestination.NAVIGATION_ROUTE,
    )
) {
    companion object {
        const val NAVIGATION_ROUTE = "favorites"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/$NAVIGATION_ROUTE"
    }
}