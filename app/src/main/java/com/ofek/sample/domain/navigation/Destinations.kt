package com.ofek.sample.domain.navigation

sealed class Destination(
    val route: String,
    val arguments: Map<String, String> = emptyMap()
)

class OnBoardingDestination() : Destination(
    route = NAVIGATION_ROUTE,
) {
    companion object {
        const val NAVIGATION_ROUTE = "onborading"
        const val DECLARATION_DESTINATION_ROUTE = NAVIGATION_ROUTE
    }
}

class StoriesDestination : Destination(
    route = NAVIGATION_ROUTE
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
        const val DECLARATION_DESTINATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_DESTINATION_ROUTE}/${FavoritesDestination.NAVIGATION_ROUTE}"
    }
}

class ArticlesDestination : Destination(
    route = NAVIGATION_ROUTE
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
        const val DECLARATION_DESTINATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_DESTINATION_ROUTE}/${FavoritesDestination.NAVIGATION_ROUTE}"
    }
}

class FavoritesDestination : Destination(
    route = NAVIGATION_ROUTE
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
        const val DECLARATION_DESTINATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_DESTINATION_ROUTE}/${NAVIGATION_ROUTE}"
    }
}