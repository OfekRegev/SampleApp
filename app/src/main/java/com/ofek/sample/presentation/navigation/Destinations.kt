package com.ofek.sample.presentation.navigation

import com.ofek.sample.domain.feed.FeedType

/**
 * route - the destination navigation route
 * arguments - optional arguments adding to route
 * ancestorRoutes - the routes that should come before this destination
 */
sealed class Destination(
    val route: String,
    val arguments: Map<String, String> = emptyMap(),
    val ancestors: List<String>
)

class OnBoardingDestination() : Destination(
    route = NAVIGATION_ROUTE,
    ancestors = emptyList()
) {
    companion object {
        const val NAVIGATION_ROUTE = "onborading"
        const val DECLARATION_ROUTE = NAVIGATION_ROUTE
    }
}

abstract class FeedDestination(
    route: String,
) : Destination(
    route = route,
    ancestors = listOf(
        OnBoardingDestination.NAVIGATION_ROUTE,
    )
) {
    companion object {
        const val FEED_TYPE_ARGUMENT_KEY: String = "feedType"
        const val DECLARATION_ROUTE =
            "${OnBoardingDestination.DECLARATION_ROUTE}/{$FEED_TYPE_ARGUMENT_KEY}"

        fun feedType(feedTypeParam: String): FeedType {
            return when (feedTypeParam) {
                StoriesDestination.NAVIGATION_ROUTE -> {
                    FeedType.STORIES
                }
                ArticlesDestination.NAVIGATION_ROUTE -> {
                    FeedType.ARTICLES
                }
                FavoritesDestination.NAVIGATION_ROUTE -> {
                    FeedType.FAVORITES
                }
                else -> {
                    // default feed
                    FeedType.ARTICLES
                }
            }
        }
    }
}

class StoriesDestination : FeedDestination(
    route = NAVIGATION_ROUTE,
) {
    companion object {
        const val NAVIGATION_ROUTE = "stories"
    }
}

class ArticlesDestination : FeedDestination(
    route = NAVIGATION_ROUTE,
) {
    companion object {
        const val NAVIGATION_ROUTE = "articles"
    }
}

class FavoritesDestination : FeedDestination(
    route = NAVIGATION_ROUTE,
) {
    companion object {
        const val NAVIGATION_ROUTE = "favorites"
    }
}

class PostDestination : Destination(
    route = NAVIGATION_ROUTE,
    ancestors = listOf(
        OnBoardingDestination.NAVIGATION_ROUTE,
        FeedDestination.DECLARATION_ROUTE,
    )
) {
    companion object {
        const val NAVIGATION_ROUTE = "post"
        private const val POST_ID_DECLARATION_PARAM = "postId={postId}"
        const val DECLARATION_ROUTE =
            "${FeedDestination.DECLARATION_ROUTE}/$NAVIGATION_ROUTE?$POST_ID_DECLARATION_PARAM"
    }
}

