package com.ofek.sample.presentation.navigation

sealed class Destination(
    val route: String,
    val arguments: Map<String, String> = emptyMap(),
    val rootDestination: Boolean,
)

class OnBoardingDestination() : Destination(
    route = NAVIGATION_ROUTE,
    rootDestination = true
) {
    companion object {
        const val NAVIGATION_ROUTE = "onborading"
        const val DECLARATION_ROUTE = NAVIGATION_ROUTE
    }
}

class StoriesDestination : Destination(
    route = NAVIGATION_ROUTE,
    rootDestination = false
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/${NAVIGATION_ROUTE}"
    }
}

class ArticlesDestination : Destination(
    route = NAVIGATION_ROUTE,
    rootDestination = false
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/${NAVIGATION_ROUTE}"
    }
}

class FavoritesDestination : Destination(
    route = NAVIGATION_ROUTE,
    rootDestination = false
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/$NAVIGATION_ROUTE"
    }
}